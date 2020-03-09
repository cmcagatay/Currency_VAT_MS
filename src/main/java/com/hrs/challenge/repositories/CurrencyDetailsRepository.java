package com.hrs.challenge.repositories;

import com.hrs.challenge.model.CurrencyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDetailsRepository extends JpaRepository<CurrencyDetails, Long> {
}
