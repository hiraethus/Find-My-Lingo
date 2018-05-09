package com.clackjones.cymraeg.gwasanaeth.web;

import java.io.Serializable;

public class GwasanaethSearchCriteria implements Serializable {
    private String language;
    private String category;
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
