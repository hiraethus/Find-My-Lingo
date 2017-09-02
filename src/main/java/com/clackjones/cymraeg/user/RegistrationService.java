package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private UserPassValidator userPassValidator;
    @Autowired
    private PasswordEncryption passwordEncryption;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordResetTokenDao tokenDao;


    public boolean register(RegistrationDetails details) throws RegistrationException {
        userPassValidator.validate(details);

        if (jdbcUserDetailsManager.userExists(details.getUsername())) {
            throw new RegistrationException("This user already exists", RegistrationExceptionType.USER_ALREADY_EXISTS);
        }

        passwordEncryption.encryptPassword(details);

        jdbcUserDetailsManager.createUser(user(details));
        jdbcUserDetailsManager.addUserToGroup(details.getUsername(), "end_users");

        return true;
    }

    private User user(RegistrationDetails details) {
        return new User(details.getUsername(), details.getPassword(), Collections.emptyList());
    }

    public boolean userExists(String email) {
        return jdbcUserDetailsManager.userExists(email);
    }

    @Transactional
    public String createResetToken(RegistrationDetails regDetails) {
        String email = regDetails.getUsername();

        UserEntity userEntity = userDao.findById(email);
        String token = UUID.randomUUID().toString();
        tokenDao.persist(new PasswordResetTokenEntity(token, userEntity));

        return token;
    }
}
