package com.clackjones.cymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GwasanaethManager {

    @Autowired
    private SylwToSylwEntityMapper sylwToEntity;

    @Autowired
    private SylwEntityToSylwMapper entityToSylw;

    @Autowired
    private SylwDao sylwDao;

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @Autowired
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;

    /**
     * @return the ID of the persisted Gwasanaeth
     */
    @Transactional
    public Long saveGwasanaeth(Gwasanaeth gwasanaeth) {
        GwasanaethEntity gwasanaethEntity = gwasanaethToEntity.map(gwasanaeth);
        gwasanaethDao.persist(gwasanaethEntity);

        return gwasanaethEntity.getId();
    }

    @Transactional
    public void addSylwForGwasanaethWithId(long gwasanaethId, Sylw sylw) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(gwasanaethId);

        if (gwasanaethEntity == null) {
            // TODO add error - gwasanaeth doesn't exist
            throw new NullPointerException();
        }

        // TODO add validator for sylw
        SylwEntity sylwEntity = sylwToEntity.map(sylw);
        sylwEntity.setGwasanaeth(gwasanaethEntity);
        sylwDao.persist(sylwEntity);

        gwasanaethEntity.getSylwadau().add(sylwEntity);
    }
}
