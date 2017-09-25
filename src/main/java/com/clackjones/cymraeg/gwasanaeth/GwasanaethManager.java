package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GwasanaethManager {
    final static Logger logger = LoggerFactory.getLogger(GwasanaethManager.class);

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
    public List<Gwasanaeth> freeSearchByNameAndCity(String searchTerm, String dinasToFilter, String categoriToFilter) {
        logger.info("Searching for Services by name and city with search term {}", searchTerm);
        logger.info("Searching for Services filtered by city: {} and categori: {}", dinasToFilter, categoriToFilter);

        Collection<GwasanaethEntity> result;
        if (searchTerm != null) {
            result = gwasanaethDao.findByEnwOrDinas(searchTerm);
        } else {
            result = gwasanaethDao.findAll();
        }
        Stream<Gwasanaeth> gwasanaethauStream = result.stream()
                .map(entityToGwasanaeth::map)
                .filter(g -> matchesDinas(g, dinasToFilter))
                .filter(g -> matchesCategori(g, categoriToFilter));

        Stream<Gwasanaeth> sortedResults = gwasanaethauStream.sorted(Comparator.comparing(Gwasanaeth::getCyfeiriadDinas, String::compareToIgnoreCase)
                .thenComparing(Comparator.comparing(Gwasanaeth::getEnw, String::compareToIgnoreCase)));

        return sortedResults.collect(Collectors.toList());
    }

    private boolean matchesDinas(Gwasanaeth gwasanaeth, String dinas) {
        return dinas == null || gwasanaeth.getCyfeiriadDinas().equals(dinas);
    }

    private boolean matchesCategori(Gwasanaeth gwasanaeth, String categori) {
        return categori == null || gwasanaeth.getCategori().getCategori().equals(categori);
    }

    /**
     * @return the ID of the persisted Gwasanaeth
     */
    @Transactional
    public Long saveGwasanaeth(Gwasanaeth gwasanaeth, String username) {
        GwasanaethEntity gwasanaethEntity = gwasanaethToEntity.map(gwasanaeth);
        gwasanaethEntity.setOwnerUsername(username);
        gwasanaethDao.persist(gwasanaethEntity);

        return gwasanaethEntity.getId();
    }

    @Transactional
    public void addSylwForGwasanaethWithId(long gwasanaethId, Sylw sylw) {
        logger.info("Adding comment '{}' to Service number {}", gwasanaethId, sylw.getSylw());
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(gwasanaethId);

        if (gwasanaethEntity == null) {
            logger.warn("Could not add comment because service number {} doesn't exist", gwasanaethId);
            // TODO add error - gwasanaeth doesn't exist
            throw new NullPointerException();
        }

        // TODO add validator for sylw
        SylwEntity sylwEntity = sylwToEntity.map(sylw);
        sylwEntity.setGwasanaeth(gwasanaethEntity);
        sylwDao.persist(sylwEntity);

        gwasanaethEntity.getSylwadau().add(sylwEntity);
    }

    @Transactional
    public void updateGwasanaeth(Gwasanaeth gwasanaeth, String name) throws GwasanaethNotFound, NoPermissionException {
        logger.info("Updating details for Service number {}", gwasanaeth.getId());
        if (gwasanaeth.getId() == null) {
            throw new NullPointerException("Gwasanaeth ID not provided");
        }

        GwasanaethEntity entity = gwasanaethDao.findById(gwasanaeth.getId());
        if (entity  == null) {
            throw new GwasanaethNotFound(String.format("Couldn't find Gwasanaeth with id %d\n", gwasanaeth.getId()));
        }

        if (!entity.getOwnerUsername().equals(name)) {
            throw new NoPermissionException(
                    String.format("User %s doesn't have permission to modify this gwasanaeth",name));
        }

        gwasanaethToEntity.map(gwasanaeth, entity);
        gwasanaethDao.merge(entity);
    }
}
