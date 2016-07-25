package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.stereotype.Component;

@Component
class SylwEntityToSylwMapper {

    public Sylw map(SylwEntity sylwEntity) {
        Sylw sylw = new Sylw();

        sylw.setId(sylwEntity.getId());
        sylw.setSafonArwyddiaeth(map(sylwEntity.getSafonArwyddiaeth()));
        sylw.setSafonGwasanaeth(map(sylwEntity.getSafonGwasanaeth()));
        sylw.setSafonIaith(map(sylwEntity.getSafonIaith()));

        sylw.setSylw(sylwEntity.getSylw());
        sylw.setAmserSylw(sylwEntity.getAmserSylw());

        return sylw;
    }

    private SafonEnum map(Safon safon) {
        switch (safon) {
            case DA:
                return SafonEnum.DA;
            case GWAEL:
                return SafonEnum.GWAEL;
            case GWEDDOL:
                return SafonEnum.GWEDDOL;
            default:
                return null;
        }
    }
}
