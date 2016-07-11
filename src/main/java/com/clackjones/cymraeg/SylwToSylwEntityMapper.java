package com.clackjones.cymraeg;

import org.springframework.stereotype.Component;

@Component
public class SylwToSylwEntityMapper {

    public SylwEntity map(Sylw sylw) {
        SylwEntity sylwEntity = new SylwEntity();

        sylwEntity.setSafonArwyddiaeth(map(sylw.getSafonArwyddiaeth()));
        sylwEntity.setSafonGwasanaeth(map(sylw.getSafonGwasanaeth()));
        sylwEntity.setSafonIaith(map(sylw.getSafonIaith()));

        sylwEntity.setSylw(sylw.getSylw());
        sylwEntity.setAmserSylw(sylw.getAmserSylw());

        return sylwEntity;
    }

    private Safon map(SafonEnum safonEnum) {
        switch (safonEnum) {
            case DA:
                return Safon.DA;
            case GWAEL:
                return Safon.GWAEL;
            case GWEDDOL:
                return Safon.GWEDDOL;
            default:
                return null;
        }
    }
}
