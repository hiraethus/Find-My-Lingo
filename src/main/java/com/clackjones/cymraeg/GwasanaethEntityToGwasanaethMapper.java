package com.clackjones.cymraeg;

import org.springframework.stereotype.Component;

@Component
public class GwasanaethEntityToGwasanaethMapper {
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

        return gwasanaeth;
    }
}