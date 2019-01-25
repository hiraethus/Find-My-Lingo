package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.dao.Dao;
import com.clackjones.cymraeg.dao.JpaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
class PasswordResetTokenDao extends JpaDao<String, PasswordResetTokenEntity>
        implements Dao<String, PasswordResetTokenEntity> {
    public PasswordResetTokenEntity findByEmailAndToken(UserEntity user, String token) {
        TypedQuery<PasswordResetTokenEntity> query =
                entityManager.createNamedQuery("PasswordResetTokenEntity.findByEmailAndToken",
                        PasswordResetTokenEntity.class);

        query.setParameter("user", user);
        query.setParameter("token", token);

        return query.getSingleResult();
    }
}
