package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.springframework.stereotype.Repository;

@Repository
class UserDao extends JpaDao<String, UserEntity> implements Dao<String, UserEntity> { }
