package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.language.Language;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

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
    private Language language;

    private BigDecimal longitude;
    private BigDecimal latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "Please provide a name for your Service", groups = Screen1.class)
    public String getEnw() {
        return enw;
    }

    public void setEnw(String enw) {
        this.enw = enw;
    }

    //TODO: custom @Phone annotation using JSR-303 with libphonenumber
    //TODO: see https://t.co/XyCKisEwGH
    @NotBlank(message = "Please provide a phone number for your service", groups = Screen2.class)
    @Pattern(message="The phone number format is incorrect. Valid format: 01233 445566", regexp = "\\d{5}\\s\\d{6}", groups = Screen2.class)
    public String getRhifFfon() {
        return rhifFfon;
    }

    public void setRhifFfon(String rhifFfon) {
        this.rhifFfon = rhifFfon;
    }

    @NotBlank(message = "Please provide an email address", groups = Screen2.class)
    @Email(message = "Please provide a valid email address", groups = Screen2.class)
    public String getEbost() {
        return ebost;
    }

    public void setEbost(String ebost) {
        this.ebost = ebost;
    }

    @NotBlank(message = "Please provide a description", groups = Screen3.class)
    public String getDisgrifiad() {
        return disgrifiad;
    }

    public void setDisgrifiad(String disgrifiad) {
        this.disgrifiad = disgrifiad;
    }

    @NotBlank(message = "Please provide address first line", groups = Screen2.class)
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

    @NotBlank(message = "Please provide a city", groups = Screen2.class)
    public String getCyfeiriadDinas() {
        return cyfeiriadDinas;
    }

    public void setCyfeiriadDinas(String cyfeiriadDinas) {
        this.cyfeiriadDinas = cyfeiriadDinas;
    }

    @NotBlank(message = "Please provide a county", groups = Screen2.class)
    public String getCyfeiriadSir() {
        return cyfeiriadSir;
    }

    public void setCyfeiriadSir(String cyfeiriadSir) {
        this.cyfeiriadSir = cyfeiriadSir;
    }

    @Pattern(message = "Please provide a valid postal code", regexp = "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})", groups = Screen2.class)
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

    interface Screen1 {}
    interface Screen2 {}
    interface Screen3 {}
}
