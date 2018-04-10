package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
class UserDao extends JpaDao<String, UserEntity> implements Dao<String, UserEntity> {
    public boolean nicknameExists(String nickname) {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT COUNT(*) FROM users u WHERE u.nickname = :nick",
                Integer.class);

        query.setParameter("nick", nickname);

        return query.getSingleResult() > 0;
    }
}
