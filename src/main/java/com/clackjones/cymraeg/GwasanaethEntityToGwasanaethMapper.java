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

        return gwasanaeth;
    }
}