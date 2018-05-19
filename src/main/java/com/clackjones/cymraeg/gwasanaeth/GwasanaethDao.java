package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class GwasanaethDao extends JpaDao<Long, GwasanaethEntity> implements Dao<Long, GwasanaethEntity> {
    final static Logger logger = LoggerFactory.getLogger(GwasanaethDao.class);

    public Collection<GwasanaethEntity> findAll() {
        logger.trace("findAll()");

        TypedQuery<GwasanaethEntity> query = entityManager.createNamedQuery("GwasanaethEntity.findAll", GwasanaethEntity.class);
        return query.getResultList();
    }

    public Collection<GwasanaethEntity> findByCityOrServiceName(String searchTerm) {
        logger.trace("findByCityOrServiceName({})", searchTerm);

        TypedQuery<GwasanaethEntity> query =
                entityManager.createNamedQuery("GwasanaethEntity.findByCityOrServiceName", GwasanaethEntity.class)
                .setParameter("name", "%"+searchTerm+"%")
                .setParameter("city", "%"+searchTerm+"%");

        return query.getResultList();
    }

    public Collection<GwasanaethEntity> findByCategoryAndCity(String category, String city) {
        logger.trace("findByCategoryAndCity");

        TypedQuery<GwasanaethEntity> query =
                entityManager.createNamedQuery("GwasanaethEntity.findByCategoryAndCity", GwasanaethEntity.class)
                        .setParameter("category", "%"+category+"%")
                        .setParameter("city", "%"+city+"%");

        return query.getResultList();
    }

    public List<String> findUniqueFirstCharacters() {
        logger.trace("findUniqueFirstCharacters()");

        TypedQuery<String> query = entityManager.createNamedQuery("GwasanaethEntity.findUniqueFirstCharacters", String.class);

        List<String> result = query.getResultList();

        return result;

    }
}
