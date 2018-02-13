package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
class GwasanaethDao extends JpaDao<Long, GwasanaethEntity> implements Dao<Long, GwasanaethEntity> {
    final static Logger logger = LoggerFactory.getLogger(GwasanaethDao.class);

    public Collection<GwasanaethEntity> findAll() {
        logger.trace("findAll()");

        TypedQuery<GwasanaethEntity> query = entityManager.createNamedQuery("GwasanaethEntity.findAll", GwasanaethEntity.class);
        return query.getResultList();
    }

    public Collection<GwasanaethEntity> findByEnwOrDinas(String searchTerm) {
        logger.trace("findByEnwOrDinas({})", searchTerm);

        TypedQuery<GwasanaethEntity> query =
                entityManager.createNamedQuery("GwasanaethEntity.findByEnwOrDinas", GwasanaethEntity.class)
                .setParameter(1, "%"+searchTerm+"%")
                .setParameter(2, "%"+searchTerm+"%");

        return query.getResultList();
    }
}
