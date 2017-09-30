package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.address.GeoLocation;
import com.clackjones.cymraeg.address.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GwasanaethEntityToGwasanaethMapper {
    private LocationService locationService;
    private CategoriEntityToCategoriMapper entityToCategory;
    private SylwEntityToSylwMapper entityToSylw;

    @Autowired
    public GwasanaethEntityToGwasanaethMapper
            (
                CategoriEntityToCategoriMapper entityToCategory,
                SylwEntityToSylwMapper entityToSylw,
                LocationService locationService
            ) {
        this.entityToCategory = entityToCategory;
        this.entityToSylw = entityToSylw;
        this.locationService = locationService;
    }

    public Gwasanaeth map(GwasanaethEntity entity) {
        Gwasanaeth gwasanaeth = new Gwasanaeth();
        gwasanaeth.setId(entity.getId());
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

        if (entity.hasSylwadau()) {
            Collection<Sylw> sylwadau = entity.getSylwadau().stream()
                    .map(sylwEntity -> entityToSylw.map(sylwEntity))
                    .collect(Collectors.toList());

            gwasanaeth.setSylwadau(sylwadau);
        }

        if (entity.getOwnerUsername() != null) gwasanaeth.setOwner(entity.getOwnerUsername());

        Optional<GeoLocation> location = locationService.findLocationForPostcode(gwasanaeth.getCyfeiriadCodPost());
        gwasanaeth.setGeoLocation(location.orElse(null));

        return gwasanaeth;
    }
}