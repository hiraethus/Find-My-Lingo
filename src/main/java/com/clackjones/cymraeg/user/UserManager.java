package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManager {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEntityToUserMapper entityToUserMapper;

    @Transactional
    public User findUserByUsername(String username) {
        UserEntity userEntity = userDao.findById(username);
        if (userEntity == null) {
            return null;
        }

        return entityToUserMapper.map(userEntity);
    }
}
