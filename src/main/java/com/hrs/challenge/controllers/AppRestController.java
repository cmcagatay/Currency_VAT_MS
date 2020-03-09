package com.hrs.challenge.controllers;

import com.hrs.challenge.dtos.CurrencyDTO;
import com.hrs.challenge.dtos.VatDTO;
import com.hrs.challenge.model.CurrencyDetails;
import com.hrs.challenge.model.VatDetails;
import com.hrs.challenge.services.CurrencyDetailsService;
import com.hrs.challenge.services.VatDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;


@RestController
public class AppRestController {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private CurrencyDetailsService currencyDetailsService;

    private VatDetailService vatDetailService;

    private ModelMapper modelMapper;

    @Autowired
    public AppRestController(CurrencyDetailsService currencyDetailsService, ModelMapper modelMapper, VatDetailService vatDetailService) {
        this.currencyDetailsService = currencyDetailsService;
        this.modelMapper = modelMapper;
        this.vatDetailService = vatDetailService;
    }


    @ApiOperation(value = "The current time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved request")
    })
    @GetMapping("/")
    public ResponseEntity getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        TimeZone clientTimeZone = calendar.getTimeZone();
        LocalDateTime localDate = LocalDateTime.now(ZoneId.of(clientTimeZone.getID()));
        JSONObject postBodyJson = new JSONObject();
        postBodyJson.put("CurrentTime", dtf.format(localDate));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(postBodyJson.toString());
    }

    @ApiOperation(value = "country (code) for VAT number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved request"),
            @ApiResponse(code = 400, message = "Invalid VAT number")
    })
    @GetMapping("vatNumber/{vatNumber}")
    public ResponseEntity getCountryCodeForVatNumber(@PathVariable String vatNumber){

        VatDetails vatDetails = vatDetailService.getVatDetail(vatNumber);

        /* We can error message like this or we can set in the @ApiResponse annotation for error code
        if vatDetails service is null. service will return error message */
        if (vatDetails == null){
            JSONObject errorMessage = new JSONObject();
            errorMessage.put("errorMessage", "Invalid VAT number");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorMessage.toString());
        }
        return ResponseEntity.ok(convertVatDetailsEntityToVatDto(vatDetails));
    }


    @ApiOperation(value = "Convert currency")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved request"),
            @ApiResponse(code = 400, message = "Incorrect target currency")
    })
    @GetMapping("/api/currency/convert/amount/{amount}/source-currency/{sourceCurrency}/target-currency/{targetCurrency}")
    public ResponseEntity<CurrencyDTO> currencyConvert(@PathVariable double amount, @PathVariable String sourceCurrency, @PathVariable String targetCurrency){

        /* We need this control because http://api.currencylayer.com service give us limited access.
           This service has just give information us about USD for source currency. */
        if (!sourceCurrency.equals("USD")){
            return ResponseEntity
                    .status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                    .body(new CurrencyDTO(0));
        }

        CurrencyDetails currencyDetails = currencyDetailsService.getCurrencyDetails(targetCurrency, sourceCurrency, amount);

        // if http://api.currencylayer.com is return null. We will return status BadRequest.
        if (currencyDetails == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CurrencyDTO(0));
        }

        CurrencyDTO currencyDTO = convertCurrencyDetailsEntityToCurrencyDto(currencyDetails);

        //if Target currency amount is negative. Our service will return, result status 400 and return "0"
        if (currencyDTO.getAmountTargetCurrency() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CurrencyDTO(0));
        }
        return ResponseEntity.ok(currencyDTO);
    }

    // convert Entity to DTO
    private CurrencyDTO convertCurrencyDetailsEntityToCurrencyDto(CurrencyDetails currrencyObj) {
        CurrencyDTO currencyDTO = modelMapper.map(currrencyObj, CurrencyDTO.class);
        currencyDTO.setAmountTargetCurrency(currrencyObj.getAmountTargetCurrency());
        return currencyDTO;
    }

    // convert Entity to DTO
    private VatDTO convertVatDetailsEntityToVatDto(VatDetails vatDetails) {
        VatDTO vatDTO = modelMapper.map(vatDetails, VatDTO.class);
        vatDTO.setCountryCode(vatDetails.getCountryCode());
        return vatDTO;
    }

}
