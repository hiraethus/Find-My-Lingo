package com.clackjones.cymraeg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GwasanaethEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String enw;
    private String rhifFfon;
    private String ebost;
    private String disgrifiad;
    private CyfeiriadEntity cyfeiriad;

    public Long getId() {
        return id;
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

    public CyfeiriadEntity getCyfeiriad() {
        return this.cyfeiriad;
    }

    public void setCyfeiriad(CyfeiriadEntity cyfeiriad) {
        this.cyfeiriad = cyfeiriad;
    }
}
