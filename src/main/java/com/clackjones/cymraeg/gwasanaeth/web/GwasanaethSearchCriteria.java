package com.clackjones.cymraeg.gwasanaeth.web;

import java.io.Serializable;
import java.math.BigDecimal;

public class GwasanaethSearchCriteria implements Serializable {
    private String language;
    private Long categoryId;
    private String category;
    private String city;
    private String languageImg;
    private String categoryImg;

    private BigDecimal longitude;
    private BigDecimal latitude;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLanguageImg(String languageImg) {
        this.languageImg = languageImg;
    }

    public String getLanguageImg() {
        return languageImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryImg() {
        return categoryImg;
    }
}
