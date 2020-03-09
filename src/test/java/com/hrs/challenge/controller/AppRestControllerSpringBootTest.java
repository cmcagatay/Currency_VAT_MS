package com.hrs.challenge.controller;

import com.hrs.challenge.dtos.CurrencyDTO;
import com.hrs.challenge.dtos.VatDTO;
import com.hrs.challenge.model.CurrencyDetails;
import com.hrs.challenge.model.VatDetails;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//I used SpringBootTest library in this class for testing
@SpringBootTest
class AppRestControllerSpringBootTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenCurrencyDetailsConvertToCurrencyDTO_thenCorrect() {
        CurrencyDetails currencyDetails = new CurrencyDetails();
        currencyDetails.setAmountTargetCurrency(10.0);
        currencyDetails.setTargetCurrency("UER");
        currencyDetails.setAmountSourceCurrency(12.0);
        currencyDetails.setSourceCurrency("USD");

        CurrencyDTO currencyConvertDTO = modelMapper.map(currencyDetails, CurrencyDTO.class);
        assertEquals(currencyDetails.getAmountTargetCurrency(), currencyConvertDTO.getAmountTargetCurrency());
    }

    @Test
    void whenVatDetailsConvertToVatDTO_thenCorrect() {
        VatDetails vatDetails = new VatDetails();

        vatDetails.setCountryCode("DE");
        vatDetails.setBusinessCountry("Germany");
        vatDetails.setBusinessCity("Berlin");
        vatDetails.setBusinessName("IT");

        VatDTO vatDTO = modelMapper.map(vatDetails, VatDTO.class);
        assertEquals(vatDetails.getCountryCode(), vatDTO.getCountryCode());
    }


}