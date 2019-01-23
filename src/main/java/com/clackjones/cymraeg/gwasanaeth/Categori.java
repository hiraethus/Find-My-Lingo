package com.clackjones.cymraeg.gwasanaeth;

public class Categori implements java.io.Serializable {
    private Long id;
    private String categori;
    private String categoriImg;
    private Boolean canDelete;

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

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }
}
