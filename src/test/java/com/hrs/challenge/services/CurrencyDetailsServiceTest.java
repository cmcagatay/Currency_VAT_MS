package com.hrs.challenge.services;

import com.hrs.challenge.model.CurrencyDetails;
import com.hrs.challenge.repositories.CurrencyDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class CurrencyDetailsServiceTest {

    @Autowired
    CurrencyDetailsRepository currencyDetailsRepository;

    @Test
    void saveCurrencyDetails() {
        CurrencyDetails currencyDetails = new CurrencyDetails();

        currencyDetails.setId(1L);
        currencyDetails.setAmountSourceCurrency(100.0);
        currencyDetails.setAmountTargetCurrency(80.0);
        currencyDetails.setSourceCurrency("USD");
        currencyDetails.setTargetCurrency("EUR");

        currencyDetailsRepository.save(currencyDetails);

        //when
        CurrencyDetails found = currencyDetailsRepository.findById(currencyDetails.getId()).get();

        //then
        assertThat(found.getAmountTargetCurrency(), is(80.0));

    }

}