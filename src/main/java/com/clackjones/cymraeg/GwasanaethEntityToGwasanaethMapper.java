package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GwasanaethEntityToGwasanaethMapper {

    @Autowired
    private CategoriEntityToCategoriMapper entityToCategory;

    public Gwasanaeth map(GwasanaethEntity entity) {
        Gwasanaeth gwasanaeth = new Gwasanaeth();
        gwasanaeth.setEnw(entity.getEnw());
        gwasanaeth.setRhifFfon(entity.getRhifFfon());
        gwasanaeth.setEbost(entity.getEbost());
        gwasanaeth.setDisgrifiad(entity.getDisgrifiad());

        CyfeiriadEntity cyfEntity;
        if ((cyfEntity = entity.getCyfeiriad()) != null) {
            gwasanaeth.setCyfeiriadLlinellGyntaf(cyfEntity.getLlinellGyntaf());
            gwasanaeth.setCyfeiriadAilLinell(cyfEntity.getAilLinell());
            gwasanaeth.setCyfeiriadDinas(cyfEntity.getDinas());
            gwasanaeth.setCyfeiriadSir(cyfEntity.getSir());
            gwasanaeth.setCyfeiriadCodPost(cyfEntity.getCodPost());
        }

        if (entity.getCategori() != null) {
            Categori categori = entityToCategory.map(entity.getCategori());
            gwasanaeth.setCategori(categori);
        }

        return gwasanaeth;
    }
}