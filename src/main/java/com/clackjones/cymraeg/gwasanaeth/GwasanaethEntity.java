package com.clackjones.cymraeg.gwasanaeth;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table("\"\"GwasanaethEntity")
@NamedQueries({
        @NamedQuery(name = "GwasanaethEntity.findAll",
                query = "SELECT g FROM GwasanaethEntity g"),
        @NamedQuery(name = "GwasanaethEntity.findByEnwOrDinas",
                query = "SELECT g from GwasanaethEntity g WHERE g.enw LIKE ? OR g.cyfeiriad.dinas LIKE ?")
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

    private String enw;
    private String rhifFfon;
    private String ebost;
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
