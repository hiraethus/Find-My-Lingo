package com.clackjones.cymraeg.gwasanaeth;

public class Categori implements java.io.Serializable {
    private Long id;
    private String categori;
    private String categoriImg;

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

    public String getCategoriImg() {
        return categoriImg;
    }

    public void setCategoriImg(String categoriImg) {
        this.categoriImg = categoriImg;
    }
}
