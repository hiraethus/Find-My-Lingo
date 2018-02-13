package com.clackjones.cymraeg.gwasanaeth;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CyfeiriadEntity {

    @Column(name = "\"llinellGyntaf\"")
    protected String llinellGyntaf;

    @Column(name = "\"ailLinell\"")
    protected String ailLinell;

    @Column(name = "\"dinas\"")
    protected String dinas;

    @Column(name = "\"sir\"")
    protected String sir;

    @Column(name = "\"codPost\"")
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
