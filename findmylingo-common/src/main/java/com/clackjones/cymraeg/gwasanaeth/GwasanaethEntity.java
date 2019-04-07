package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.language.LanguageEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="fml_service")
@NamedQueries({
        @NamedQuery(name = "GwasanaethEntity.findAll",
                query = "SELECT g FROM GwasanaethEntity g"),
        @NamedQuery(name = "GwasanaethEntity.findByCityOrServiceName",
                query = "SELECT g from GwasanaethEntity g WHERE UPPER(g.enw) LIKE UPPER(:name) OR UPPER(g.cyfeiriad.dinas) LIKE UPPER(:city)"),
        @NamedQuery(name = "GwasanaethEntity.findUniqueFirstCharacters",
                query = "SELECT DISTINCT UPPER(SUBSTRING(g.enw, 1, 1)) AS first_letter FROM GwasanaethEntity g ORDER BY first_letter ASC"),
        @NamedQuery(name = "GwasanaethEntity.findByCategoryAndCity",
                query = "SELECT g FROM GwasanaethEntity g WHERE g.categori.id = :category AND UPPER(g.cyfeiriad.dinas) LIKE UPPER(:city)"
        ),
        @NamedQuery(name = "GwasanaethEntity.findByCategoryAndLanguage",
                query = "SELECT g FROM GwasanaethEntity g WHERE (:category is null OR g.categori.id = :category) AND (:language is null OR UPPER(g.language.nativeLangName) LIKE UPPER(:language))"
        )
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

    @Column(name = "name")
    private String enw;

    @Column(name = "phone_number")
    private String rhifFfon;

    @Column(name = "email")
    private String ebost;

    @Column(name = "description")
    private String disgrifiad;

    private CyfeiriadEntity cyfeiriad;

    @OneToMany(mappedBy = "gwasanaeth")
    private Collection<SylwEntity> sylwadau = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="categori_id")
    private CategoriEntity categori;

    @Column(name = "owner_username", nullable = false)
    private String ownerUsername;

    @ManyToOne
    private LanguageEntity language;

    @Column(name = "longitude", nullable = true)
    private BigDecimal longitude;

    @Column(name = "latitude", nullable = true)
    private BigDecimal latitude;

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

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
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
