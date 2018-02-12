package com.clackjones.cymraeg.gwasanaeth;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="\"CategoriEntity\"")
@NamedQueries({
        @NamedQuery(name = "CategoriEntity.findAll",
                query = "SELECT c FROM CategoriEntity c")
})
class CategoriEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String categori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { }

    @OneToMany
    protected Collection<GwasanaethEntity> gwasanaethau;

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public Collection<GwasanaethEntity> getGwasanaethau() {
        return gwasanaethau;
    }

    public void setGwasanaethau(Collection<GwasanaethEntity> gwasanaethau) {
        this.gwasanaethau = gwasanaethau;
    }
}
