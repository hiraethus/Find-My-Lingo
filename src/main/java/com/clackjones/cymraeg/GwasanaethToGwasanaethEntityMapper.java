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

        return entity;
    }
}
