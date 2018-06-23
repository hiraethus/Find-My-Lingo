package com.clackjones.cymraeg.gwasanaeth;


import com.clackjones.cymraeg.language.Language;
import com.clackjones.cymraeg.language.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.clackjones.cymraeg.language.LanguageDao;

@Component
class GwasanaethToGwasanaethEntityMapper {
    private CategoriDao categoriDao;
    private LanguageDao languageDao;

    @Autowired
    public GwasanaethToGwasanaethEntityMapper(CategoriDao categoriDao, LanguageDao languageDao) {
        this.categoriDao = categoriDao;
        this.languageDao = languageDao;
    }

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
            setCategori(entity, gwasanaeth);
        }

        if (gwasanaeth.getLanguage() != null) {
            setLanguage(entity, gwasanaeth);
        }

        if (gwasanaeth.getLongitude() != null) {
            entity.setLongitude(gwasanaeth.getLongitude());
            entity.setLatitude(gwasanaeth.getLatitude());
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

    private void setCategori(GwasanaethEntity entity, Gwasanaeth gwasanaeth) {
        Categori categori = gwasanaeth.getCategori();
        if (categori != null) {
            // check to see if its in the database
            CategoriEntity categoriEntity = categoriDao.findById(categori.getId());
            if (categoriEntity != null) {
                entity.setCategori(categoriEntity);
            }
        }
    }

    private void setLanguage(GwasanaethEntity entity, Gwasanaeth gwasanaeth) {
        Language language = gwasanaeth.getLanguage();
        if (language != null) {
            LanguageEntity languageEntity = languageDao.findById(language.getId());
            if (languageEntity != null) {
                entity.setLanguage(languageEntity);
            }
        }
    }
}
