package com.clackjones.cymraeg.gwasanaeth;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
class SylwEntity {
    @Id
    @GenericGenerator(
            name = "sylw-sequence",
            strategy = "enhanced-table",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "table_name",
                            value = "sylw_sequence_table"
                    )
            })
    @GeneratedValue(generator = "sylw-sequence", strategy= GenerationType.TABLE)
    private Long id;

    private @Enumerated(EnumType.STRING) Safon safonArwyddiaeth;
    private @Enumerated(EnumType.STRING) Safon safonIaith;
    private @Enumerated(EnumType.STRING) Safon safonGwasanaeth;

    @Column(columnDefinition = "text")
    private String sylw;

    @Temporal(TemporalType.TIME)
    private Date amserSylw;

    @ManyToOne
    private GwasanaethEntity gwasanaeth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Safon getSafonArwyddiaeth() {
        return safonArwyddiaeth;
    }

    public void setSafonArwyddiaeth(Safon safonArwyddiaeth) {
        this.safonArwyddiaeth = safonArwyddiaeth;
    }

    public Safon getSafonIaith() {
        return safonIaith;
    }

    public void setSafonIaith(Safon safonIaith) {
        this.safonIaith = safonIaith;
    }

    public Safon getSafonGwasanaeth() {
        return safonGwasanaeth;
    }

    public void setSafonGwasanaeth(Safon safonGwasanaeth) {
        this.safonGwasanaeth = safonGwasanaeth;
    }

    public String getSylw() {
        return sylw;
    }

    public void setSylw(String sylw) {
        this.sylw = sylw;
    }

    public Date getAmserSylw() {
        return amserSylw;
    }

    public void setAmserSylw(Date amserSylw) {
        this.amserSylw = amserSylw;
    }

    public GwasanaethEntity getGwasanaeth() {
        return gwasanaeth;
    }

    public void setGwasanaeth(GwasanaethEntity gwasanaeth) {
        this.gwasanaeth = gwasanaeth;
    }

    @PrePersist
    void preInsertAmserSylw() {
        this.amserSylw = new Date();
    }
}
