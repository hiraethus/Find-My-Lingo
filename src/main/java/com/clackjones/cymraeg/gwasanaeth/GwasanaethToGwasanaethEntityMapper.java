package com.clackjones.cymraeg.gwasanaeth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GwasanaethToGwasanaethEntityMapper {
    @Autowired
    private CategoriDao categoriDao;

    public GwasanaethEntity map(Gwasanaeth gwasanaeth) {
        return map(gwasanaeth, new GwasanaethEntity());
    }


    public GwasanaethEntity map(Gwasanaeth gwasanaeth, GwasanaethEntity entity) {
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

        if (gwasanaeth.getCategori() != null) {
            setCategoriForGwasanaethEntity(entity, gwasanaeth);
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

    private void setCategoriForGwasanaethEntity(GwasanaethEntity entity, Gwasanaeth gwasanaeth) {
        Categori categori = gwasanaeth.getCategori();
        if (categori != null) {
            // check to see if its in the database
            CategoriEntity categoriEntity = categoriDao.findById(categori.getId());
            if (categoriEntity != null) {
                entity.setCategori(categoriEntity);
            }
        }
    }
}
