package com.hrs.challenge.services;

import com.hrs.challenge.model.CurrencyDetails;
import com.hrs.challenge.repositories.CurrencyDetailsRepository;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CurrencyDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ACCESS_KEY = "4e5dc6e719f99b8c80529f0a31745f53";
    private static final String BASE_URL = "http://api.currencylayer.com/";
    private static final String ENDPOINT = "live";

    private CurrencyDetailsRepository currencyDetailsRepository;

    // this object is used for executing requests to the (REST) API
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    public CurrencyDetailsService(CurrencyDetailsRepository currencyDetailsRepository) {
        this.currencyDetailsRepository = currencyDetailsRepository;
    }

    private void saveCurrencyDetails(CurrencyDetails currencyDetails){
        currencyDetailsRepository.save(currencyDetails);
    }

    public CurrencyDetails getCurrencyDetails(String targetCurrency, String sourceCurrency, double amount) {
        CurrencyDetails currencyDetails = new CurrencyDetails();
        currencyDetails.setTargetCurrency(targetCurrency);
        currencyDetails.setSourceCurrency(sourceCurrency);
        currencyDetails.setAmountSourceCurrency(amount);

        //There are getting is parity between target currency and source currency. Later multiplication with amount
        currencyDetails.setAmountTargetCurrency(getParity(targetCurrency, sourceCurrency) * amount);

        // Currency History object added to DB
        saveCurrencyDetails(currencyDetails);

        return currencyDetails;
    }


    private double getParity(String target, String source){
        double result = 0;
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(BASE_URL + ENDPOINT);

            //currencies parameter's first value is "To" and second value is "From"
            builder.setParameter("access_key", ACCESS_KEY)
                    .setParameter("currencies",target);

            // The following line initializes the HttpGet Object with the URL in order to send a request
            HttpGet url = new HttpGet(String.valueOf(builder));
            response =  httpClient.execute(url);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 400){
                return -1;
            }

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            //if exchange rates service return not success. my service return -1
            if (!exchangeRates.getBoolean("success")){
                return -1;
            }

            result = exchangeRates.getJSONObject("quotes").getDouble(source + target);

            response.close();

        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        } catch (JSONException e) {
            logger.error("JSONException", e);
        }catch (URISyntaxException e){
            logger.error("URISyntaxException", e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
        return result;
    }
}
