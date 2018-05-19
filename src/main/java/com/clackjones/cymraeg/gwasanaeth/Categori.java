package com.clackjones.cymraeg.gwasanaeth;

public class Categori implements java.io.Serializable {
    private Long id;
    private String categori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }
}
