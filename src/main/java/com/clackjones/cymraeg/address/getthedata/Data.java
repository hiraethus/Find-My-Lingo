package com.clackjones.cymraeg.address.getthedata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private String postcode;
    private String status;
    private String usertype;
    private long easting;
    private long northing;
    private int positionalQualityIndicator;
    private String country;
    private double latitude;
    private double longitude;
    private String postcodeNoSpace;
    private String postcodeFixedWidthSeven;
    private String postcodeFixedWidthEight;
    private String postcodeArea;
    private String postcodeDisctrict;
    private String postcodeSector;
    private String outcode;
    private String incode;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public long getEasting() {
        return easting;
    }

    public void setEasting(long easting) {
        this.easting = easting;
    }

    public long getNorthing() {
        return northing;
    }

    public void setNorthing(long northing) {
        this.northing = northing;
    }

    public int getPositionalQualityIndicator() {
        return positionalQualityIndicator;
    }

    public void setPositionalQualityIndicator(int positionalQualityIndicator) {
        this.positionalQualityIndicator = positionalQualityIndicator;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPostcodeNoSpace() {
        return postcodeNoSpace;
    }

    public void setPostcodeNoSpace(String postcodeNoSpace) {
        this.postcodeNoSpace = postcodeNoSpace;
    }

    public String getPostcodeFixedWidthSeven() {
        return postcodeFixedWidthSeven;
    }

    public void setPostcodeFixedWidthSeven(String postcodeFixedWidthSeven) {
        this.postcodeFixedWidthSeven = postcodeFixedWidthSeven;
    }

    public String getPostcodeFixedWidthEight() {
        return postcodeFixedWidthEight;
    }

    public void setPostcodeFixedWidthEight(String postcodeFixedWidthEight) {
        this.postcodeFixedWidthEight = postcodeFixedWidthEight;
    }

    public String getPostcodeArea() {
        return postcodeArea;
    }

    public void setPostcodeArea(String postcodeArea) {
        this.postcodeArea = postcodeArea;
    }

    public String getPostcodeDisctrict() {
        return postcodeDisctrict;
    }

    public void setPostcodeDisctrict(String postcodeDisctrict) {
        this.postcodeDisctrict = postcodeDisctrict;
    }

    public String getPostcodeSector() {
        return postcodeSector;
    }

    public void setPostcodeSector(String postcodeSector) {
        this.postcodeSector = postcodeSector;
    }

    public String getOutcode() {
        return outcode;
    }

    public void setOutcode(String outcode) {
        this.outcode = outcode;
    }

    public String getIncode() {
        return incode;
    }

    public void setIncode(String incode) {
        this.incode = incode;
    }
}
