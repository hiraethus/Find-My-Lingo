package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;

    @Transactional
    public Gwasanaeth findById(Long id) {
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(id);

        return entityToGwasanaeth.map(gwasanaethEntity);
    }

    @Transactional
    public List<Gwasanaeth> findAllWithConditionsAlphabetically(String dinasToFilter, String categoriToFilter) {
        final Collection<GwasanaethEntity> gwasanaethauEntities = gwasanaethDao.findAll();
        Stream<Gwasanaeth> gwasanaethauStream = gwasanaethauEntities.stream()
                .map(gwasanaethEntity -> entityToGwasanaeth.map(gwasanaethEntity));

        if (dinasToFilter != null) {
            gwasanaethauStream = gwasanaethauStream.filter(g -> g.getCyfeiriadDinas().equals(dinasToFilter));
        }

        if (categoriToFilter != null) {
            gwasanaethauStream = gwasanaethauStream.filter(g -> g.getCategori().getCategori().equals(categoriToFilter));
        }

        gwasanaethauStream = gwasanaethauStream.sorted(Comparator.comparing(Gwasanaeth::getCyfeiriadDinas, String::compareToIgnoreCase)
                .thenComparing(Comparator.comparing(Gwasanaeth::getEnw, String::compareToIgnoreCase)));

        return gwasanaethauStream.collect(Collectors.toList());
    }

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
