package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
class GwasanaethDao extends JpaDao<Long, GwasanaethEntity> implements Dao<Long, GwasanaethEntity>  {
    public Collection<GwasanaethEntity> findAll() {
        TypedQuery<GwasanaethEntity> query = entityManager.createNamedQuery("GwasanaethEntity.findAll", GwasanaethEntity.class);
        return query.getResultList();
    }
}
