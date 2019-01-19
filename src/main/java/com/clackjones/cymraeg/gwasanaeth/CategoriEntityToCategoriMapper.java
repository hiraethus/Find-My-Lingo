package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.stereotype.Component;

@Component
public class CategoriEntityToCategoriMapper {
    public Categori map(final CategoriEntity entity) {
        Categori categori = new Categori();
        categori.setId(entity.getId());
        if (entity.getCategori() != null) {
            categori.setCategori(entity.getCategori());
        }

        return categori;
    }

}
