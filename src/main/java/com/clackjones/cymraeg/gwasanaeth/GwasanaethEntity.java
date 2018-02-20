package com.clackjones.cymraeg.gwasanaeth;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="GwasanaethEntity")
@NamedQueries({
        @NamedQuery(name = "GwasanaethEntity.findAll",
                query = "SELECT g FROM GwasanaethEntity g"),
        @NamedQuery(name = "GwasanaethEntity.findByCityOrServiceName",
                query = "SELECT g from GwasanaethEntity g WHERE g.enw LIKE :name OR g.cyfeiriad.dinas LIKE :city"),
        @NamedQuery(name = "GwasanaethEntity.findUniqueFirstCharacters",
                query = "SELECT DISTINCT UPPER(SUBSTRING(g.enw, 1, 1)) AS first_letter FROM GwasanaethEntity g ORDER BY first_letter ASC")

})
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

    @Column(name = "enw")
    private String enw;

    @Column(name = "rhifFfon")
    private String rhifFfon;

    @Column(name = "ebost")
    private String ebost;

    @Column(name = "disgrifiad")
    private String disgrifiad;

    private CyfeiriadEntity cyfeiriad;

    @OneToMany(mappedBy = "gwasanaeth")
    private Collection<SylwEntity> sylwadau = new ArrayList<>();

    @ManyToOne
    private CategoriEntity categori;

    @Column(name = "OWNER_USERNAME", nullable = false)
    private String ownerUsername;

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


    public Collection<SylwEntity> getSylwadau() {
        return sylwadau;
    }

    public void setSylwadau(Collection<SylwEntity> sylwadau) {
        this.sylwadau = sylwadau;
    }

    public boolean hasSylwadau() {
        return this.getSylwadau() != null && this.getSylwadau().size() > 0;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
