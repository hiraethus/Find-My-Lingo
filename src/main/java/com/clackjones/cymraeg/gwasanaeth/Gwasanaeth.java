package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.address.GeoLocation;
import com.clackjones.cymraeg.language.Language;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Gwasanaeth implements java.io.Serializable {

    private Long id;
    private String enw;
    private String rhifFfon;
    private String ebost;
    private String disgrifiad;
    private String cyfeiriadLlinellGyntaf;
    private String cyfeiriadAilLinell;
    private String cyfeiriadDinas;
    private String cyfeiriadSir;
    private String cyfeiriadCodPost;
    private Categori categori;
    private Collection<Sylw> sylwadau = new ArrayList<>();
    private String owner;
    private GeoLocation geoLocation;
    private Language language;

    private BigDecimal longitude;
    private BigDecimal latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnw() {
        return enw;
    }

    public void setEnw(String enw) {
        this.enw = enw;
    }

    public String getRhifFfon() {
        return rhifFfon;
    }

    public void setRhifFfon(String rhifFfon) {
        this.rhifFfon = rhifFfon;
    }

    public String getEbost() {
        return ebost;
    }

    public void setEbost(String ebost) {
        this.ebost = ebost;
    }

    public String getDisgrifiad() {
        return disgrifiad;
    }

    public void setDisgrifiad(String disgrifiad) {
        this.disgrifiad = disgrifiad;
    }

    public String getCyfeiriadLlinellGyntaf() {
        return cyfeiriadLlinellGyntaf;
    }

    public void setCyfeiriadLlinellGyntaf(String cyfeiriadLlinellGyntaf) {
        this.cyfeiriadLlinellGyntaf = cyfeiriadLlinellGyntaf;
    }

    public void setCyfeiriadAilLinell(String cyfeiriadAilLinell) {
        this.cyfeiriadAilLinell = cyfeiriadAilLinell;
    }

    public String getCyfeiriadAilLinell() {
        return cyfeiriadAilLinell;
    }

    public String getCyfeiriadDinas() {
        return cyfeiriadDinas;
    }

    public void setCyfeiriadDinas(String cyfeiriadDinas) {
        this.cyfeiriadDinas = cyfeiriadDinas;
    }

    public String getCyfeiriadSir() {
        return cyfeiriadSir;
    }

    public void setCyfeiriadSir(String cyfeiriadSir) {
        this.cyfeiriadSir = cyfeiriadSir;
    }

    public String getCyfeiriadCodPost() {
        return cyfeiriadCodPost;
    }

    public void setCyfeiriadCodPost(String cyfeiriadCodPost) {
        this.cyfeiriadCodPost = cyfeiriadCodPost;
    }

    public void setCategori(Categori categori) {
        this.categori = categori;
    }

    public Categori getCategori() {
        return categori;
    }

    public Collection<Sylw> getSylwadau() {
        return sylwadau;
    }

    public void setSylwadau(Collection<Sylw> sylwadau) {
        this.sylwadau = sylwadau;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
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
}
