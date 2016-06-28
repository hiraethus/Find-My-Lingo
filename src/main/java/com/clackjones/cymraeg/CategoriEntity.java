package com.clackjones.cymraeg;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "CategoriEntity.findAll",
                query = "SELECT c FROM CategoriEntity c")
})
public class CategoriEntity {
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
