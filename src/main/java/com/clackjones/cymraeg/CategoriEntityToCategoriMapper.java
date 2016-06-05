package com.clackjones.cymraeg;

import org.springframework.stereotype.Component;

@Component
public class CategoriEntityToCategoriMapper {
    public Categori map(final CategoriEntity entity) {
        Categori categori = new Categori();
        categori.setId(entity.getId());
        categori.setCategori(entity.getCategori());

        return categori;
    }

}
