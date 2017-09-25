package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
class GwasanaethDao extends JpaDao<Long, GwasanaethEntity> implements Dao<Long, GwasanaethEntity> {
    public Collection<GwasanaethEntity> findAll() {
        TypedQuery<GwasanaethEntity> query = entityManager.createNamedQuery("GwasanaethEntity.findAll", GwasanaethEntity.class);
        return query.getResultList();
    }

    public Collection<GwasanaethEntity> findByEnwOrDinas(String searchTerm) {
        TypedQuery<GwasanaethEntity> query =
                entityManager.createNamedQuery("GwasanaethEntity.findByEnwOrDinas", GwasanaethEntity.class)
                .setParameter(1, "%"+searchTerm+"%")
                .setParameter(2, "%"+searchTerm+"%");

        return query.getResultList();
    }
}
