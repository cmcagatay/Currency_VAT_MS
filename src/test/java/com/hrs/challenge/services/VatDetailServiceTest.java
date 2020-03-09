package com.hrs.challenge.services;

import com.hrs.challenge.model.VatDetails;
import com.hrs.challenge.repositories.VatDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class VatDetailServiceTest {

    @Autowired
    VatDetailsRepository vatDetailsRepository;

    @Test
    void saveCurrencyDetails() {
        VatDetails vatDetails = new VatDetails();

        vatDetails.setCountryCode("DE");
        vatDetails.setBusinessCountry("Germany");
        vatDetails.setBusinessCity("Berlin");
        vatDetails.setBusinessName("IT");

        vatDetailsRepository.save(vatDetails);

        //when
        VatDetails found = vatDetailsRepository.findById(vatDetails.getId()).get();

        //then
        assertThat(found.getCountryCode(), is(equalToIgnoringCase("DE")));

    }

}