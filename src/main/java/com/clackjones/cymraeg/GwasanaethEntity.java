package com.clackjones.cymraeg;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class GwasanaethEntity {
    @Id
    @GenericGenerator(
            name = "gwasanaeth-sequence",
            strategy = "enhanced-table",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "table_name",
                            value = "gwasanaeth_sequence_table"
                    )
            })
    @GeneratedValue(generator = "gwasanaeth-sequence", strategy=GenerationType.TABLE)
    private Long id;

    private String enw;
    private String rhifFfon;
    private String ebost;
    private String disgrifiad;
    private CyfeiriadEntity cyfeiriad;

    @ManyToOne
    private CategoriEntity categori;

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

    public CategoriEntity getCategori() {
        return categori;
    }

    public void setCategori(CategoriEntity categori) {
        this.categori = categori;
    }
}
