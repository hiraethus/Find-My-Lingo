package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;

public class UserManager {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEntityToUserMapper entityToUserMapper;

    public User findUserByUsername(String username) {
        UserEntity userEntity = userDao.findById(username);
        if (userEntity == null) {
            return null;
        }

        return entityToUserMapper.map(userEntity);
    }
}
