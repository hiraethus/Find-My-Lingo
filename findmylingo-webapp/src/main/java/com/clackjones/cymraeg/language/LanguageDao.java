package com.clackjones.cymraeg.language;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class LanguageDao extends JpaDao<Long, LanguageEntity> implements Dao<Long, LanguageEntity> {
    final static Logger logger = LoggerFactory.getLogger(LanguageDao.class);

    public Collection<LanguageEntity> findAll() {
        logger.trace("findAll()");

        TypedQuery<LanguageEntity> query = entityManager.createNamedQuery("LanguageEntity.findAll", LanguageEntity.class);
        return query.getResultList();
    }

}
