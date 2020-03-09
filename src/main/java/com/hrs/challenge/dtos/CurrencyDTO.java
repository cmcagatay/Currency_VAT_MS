package com.hrs.challenge.dtos;

public class CurrencyDTO {

    private double amountTargetCurrency;

    public double getAmountTargetCurrency() {
        return amountTargetCurrency;
    }

    public void setAmountTargetCurrency(double amountTargetCurrency) {
        this.amountTargetCurrency = amountTargetCurrency;
    }

    public CurrencyDTO() {
    }

    public CurrencyDTO(double amountTargetCurrency) {
        this.amountTargetCurrency = amountTargetCurrency;
    }

    @Override
    public String toString() {
        return "CurrencyConvertDTO{" +
                "amountTargetCurrency=" + amountTargetCurrency +
                '}';
    }
}
