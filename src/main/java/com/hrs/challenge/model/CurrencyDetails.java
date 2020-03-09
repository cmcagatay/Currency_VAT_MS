package com.hrs.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "CURRENCY_HISTORY")
public class CurrencyDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "SOURCE_CURRENCY")
    private String sourceCurrency;

    @Column(name = "TARGET_CURRENCY")
    private String targetCurrency;

    @Column(name = "AMOUNT_SOURCE_CURRENCY")
    private Double amountSourceCurrency;

    @Column(name = "AMOUNT_TARGET_CURRENCY")
    private Double amountTargetCurrency;

    public CurrencyDetails() {
    }

    public CurrencyDetails(Long id, String sourceCurrency, String targetCurrency, double amountSourceCurrency, double amountTargetCurrency) {
        this.id = id;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amountSourceCurrency = amountSourceCurrency;
        this.amountTargetCurrency = amountTargetCurrency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getAmountSourceCurrency() {
        return amountSourceCurrency;
    }

    public void setAmountSourceCurrency(Double amountSourceCurrency) {
        this.amountSourceCurrency = amountSourceCurrency;
    }

    public Double getAmountTargetCurrency() {
        return amountTargetCurrency;
    }

    public void setAmountTargetCurrency(Double amountTargetCurrency) {
        this.amountTargetCurrency = amountTargetCurrency;
    }
}