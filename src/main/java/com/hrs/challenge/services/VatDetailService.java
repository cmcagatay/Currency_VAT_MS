package com.hrs.challenge.services;

import com.google.gson.Gson;
import com.hrs.challenge.model.VatDetails;
import com.hrs.challenge.repositories.VatDetailsRepository;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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

@Service
public class VatDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private VatDetailsRepository vatDetailsRepository;

    private static final String ACCESS_KEY = "8340e570-8659-4540-8c12-8e208332a27f";
    private static final String BASE_URL = "http://api.cloudmersive.com/validate/vat/lookup";

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    public VatDetailService(VatDetailsRepository vatDetailsRepository) {
        this.vatDetailsRepository = vatDetailsRepository;
    }

    private void saveVatDetail(VatDetails vatDetails){
        vatDetailsRepository.save(vatDetails);
    }


    public VatDetails getVatDetail(String vatCode){
        VatDetails vatDetails = null;
        CloseableHttpResponse response = null;
        try {
            JSONObject postBodyJson = new JSONObject();
            postBodyJson.put("VatCode", vatCode);

            StringEntity entityStr = new StringEntity(postBodyJson.toString(), ContentType.APPLICATION_JSON);

            HttpPost url = new HttpPost(BASE_URL);
            url.setHeader("Apikey", ACCESS_KEY);
            url.setEntity(entityStr);

            response =  httpClient.execute(url);

            // if given VAT number is invalid. "http://api.cloudmersive.com"s service return status code 400 and our code will return null.
            if (response.getStatusLine().getStatusCode() == 400){
                return null;
            }

            HttpEntity entity = response.getEntity();

            JSONObject vatDetailJson = new JSONObject(EntityUtils.toString(entity));

            //I used GSON library for the convert json object to Java object
            Gson gson = new Gson();

            vatDetails = gson.fromJson(String.valueOf(vatDetailJson), VatDetails.class);
            saveVatDetail(vatDetails);
            response.close();
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        } catch (JSONException e) {
            logger.error("JSONException", e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
        return vatDetails;
    }
}
