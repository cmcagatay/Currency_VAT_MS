package com.hrs.challenge.model;

import javax.persistence.*;

@Entity(name = "VAT_DETAILS")
public class VatDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COUNTRY_CODE")
    private String CountryCode;

    @Column(name = "VAT_NUMBER")
    private String VatNumber;

    @Column(name = "IS_VALID")
    private boolean IsValid;

    @Column(name = "BUSINESS_NAME")
    private String BusinessName;

    @Column(name = "BUSINESS_ADDRESS")
    private String BusinessAddress;

    @Column(name = "BUSINESS_BUILDING")
    private String BusinessBuilding;

    @Column(name = "BUSINESS_STREET_NUMBER")
    private String BusinessStreetNumber;

    @Column(name = "BUSINESS_STREET")
    private String BusinessStreet;

    @Column(name = "BUSINESS_CITY")
    private String BusinessCity;

    @Column(name = "BUSINESS_STATE_OR_PROVINCE")
    private String BusinessStateOrProvince;

    @Column(name = "BUSINESS_POSTAL_CODE")
    private String BusinessPostalCode;

    @Column(name = "BUSINESS_COUNTRY")
    private String BusinessCountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getVatNumber() {
        return VatNumber;
    }

    public void setVatNumber(String vatNumber) {
        VatNumber = vatNumber;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getBusinessAddress() {
        return BusinessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        BusinessAddress = businessAddress;
    }

    public String getBusinessBuilding() {
        return BusinessBuilding;
    }

    public void setBusinessBuilding(String businessBuilding) {
        BusinessBuilding = businessBuilding;
    }

    public String getBusinessStreetNumber() {
        return BusinessStreetNumber;
    }

    public void setBusinessStreetNumber(String businessStreetNumber) {
        BusinessStreetNumber = businessStreetNumber;
    }

    public String getBusinessStreet() {
        return BusinessStreet;
    }

    public void setBusinessStreet(String businessStreet) {
        BusinessStreet = businessStreet;
    }

    public String getBusinessCity() {
        return BusinessCity;
    }

    public void setBusinessCity(String businessCity) {
        BusinessCity = businessCity;
    }

    public String getBusinessStateOrProvince() {
        return BusinessStateOrProvince;
    }

    public void setBusinessStateOrProvince(String businessStateOrProvince) {
        BusinessStateOrProvince = businessStateOrProvince;
    }

    public String getBusinessPostalCode() {
        return BusinessPostalCode;
    }

    public void setBusinessPostalCode(String businessPostalCode) {
        BusinessPostalCode = businessPostalCode;
    }

    public String getBusinessCountry() {
        return BusinessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        BusinessCountry = businessCountry;
    }
}