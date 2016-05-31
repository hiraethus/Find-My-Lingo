package com.clackjones.cymraeg;


import org.springframework.stereotype.Component;

@Component
public class GwasanaethToGwasanaethEntityMapper {
    public GwasanaethEntity map(Gwasanaeth gwasanaeth) {
        GwasanaethEntity entity = new GwasanaethEntity();
        entity.setEnw(gwasanaeth.getEnw());
        entity.setRhifFfon(gwasanaeth.getRhifFfon());
        entity.setEbost(gwasanaeth.getEbost());
        entity.setDisgrifiad(gwasanaeth.getDisgrifiad());

        if (isAddressNonEmpty(gwasanaeth)) {
            CyfeiriadEntity cyfEntity = new CyfeiriadEntity();
            cyfEntity.setLlinellGyntaf(gwasanaeth.getCyfeiriadLlinellGyntaf());
            cyfEntity.setAilLinell(gwasanaeth.getCyfeiriadAilLinell());
            cyfEntity.setDinas(gwasanaeth.getCyfeiriadDinas());
            cyfEntity.setSir(gwasanaeth.getCyfeiriadSir());
            cyfEntity.setCodPost(gwasanaeth.getCyfeiriadCodPost());

            entity.setCyfeiriad(cyfEntity);
        }

        return entity;
    }

    private boolean isAddressNonEmpty(Gwasanaeth gwasanaeth) {
        return gwasanaeth.getCyfeiriadLlinellGyntaf() != null
                || gwasanaeth.getCyfeiriadAilLinell() != null
                || gwasanaeth.getCyfeiriadDinas() != null
                || gwasanaeth.getCyfeiriadSir() != null
                || gwasanaeth.getCyfeiriadCodPost() != null;
    }
}
