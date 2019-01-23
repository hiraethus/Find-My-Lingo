package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.springframework.stereotype.Repository;

@Repository
class SylwDao extends JpaDao<Long, SylwEntity> implements Dao<Long, SylwEntity> {   }
