package com.clackjones.cymraeg.gwasanaeth;

import javax.persistence.Embeddable;

@Embeddable
public class CyfeiriadEntity {
    protected String llinellGyntaf;
    protected String ailLinell;
    protected String dinas;
    protected String sir;
    protected String codPost;

    protected CyfeiriadEntity() { }

    public String getLlinellGyntaf() {
        return llinellGyntaf;
    }

    public void setLlinellGyntaf(String llinellGyntaf) {
        this.llinellGyntaf = llinellGyntaf;
    }

    public String getAilLinell() {
        return ailLinell;
    }

    public void setAilLinell(String ailLinell) {
        this.ailLinell = ailLinell;
    }

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public String getCodPost() {
        return codPost;
    }

    public void setCodPost(String codPost) {
        this.codPost = codPost;
    }

    public String getSir() {
        return sir;
    }

    public void setSir(String sir) {
        this.sir = sir;
    }
}
