package com.clackjones.cymraeg.gwasanaeth;

import java.util.Date;

public class Sylw implements java.io.Serializable {
    private Long id;
    private SafonEnum safonArwyddiaeth;
    private SafonEnum safonIaith;
    private SafonEnum safonGwasanaeth;

    private String sylw;
    private Date amserSylw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SafonEnum getSafonArwyddiaeth() {
        return safonArwyddiaeth;
    }

    public void setSafonArwyddiaeth(SafonEnum safonArwyddiaeth) {
        this.safonArwyddiaeth = safonArwyddiaeth;
    }

    public SafonEnum getSafonIaith() {
        return safonIaith;
    }

    public void setSafonIaith(SafonEnum safonIaith) {
        this.safonIaith = safonIaith;
    }

    public SafonEnum getSafonGwasanaeth() {
        return safonGwasanaeth;
    }

    public void setSafonGwasanaeth(SafonEnum safonGwasanaeth) {
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
}
