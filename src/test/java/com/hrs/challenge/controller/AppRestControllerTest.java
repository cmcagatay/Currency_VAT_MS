package com.hrs.challenge.controller;

import com.hrs.challenge.dtos.CurrencyDTO;
import com.hrs.challenge.repositories.CurrencyDetailsRepository;
import com.hrs.challenge.services.CurrencyDetailsService;
import com.hrs.challenge.services.VatDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//I used Assertj library in this class for testing
@RunWith(SpringRunner.class)
@WebMvcTest
public class AppRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    VatDetailService vatDetailService;

    @MockBean
    CurrencyDetailsService currencyDetailsService;

    @MockBean
    CurrencyDetailsRepository currencyDetailsRepository;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    CurrencyDTO currencyDTO;

    @Test
    public void whenGetCurrentTime_thenReturnTrue() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenVatNumber_whenGetCountryCodeForVatNumber_thenReturnNull() throws Exception {

        mvc.perform(get("/vatNumber/123456asdfre"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenTargetCurrencyAndSourceCurrency_whenGetTargetCurrencyAmount_thenReturnNull() throws Exception {

        mvc.perform(get("/api/currency/convert/amount/10/source-currency/USD/target-currency/YTL"))
                .andExpect(status().isBadRequest());
    }


}
