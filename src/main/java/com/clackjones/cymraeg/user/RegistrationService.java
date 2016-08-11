package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Collections;

@Service
public class RegistrationService {
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private UserPassValidator userPassValidator;
    @Autowired
    private PasswordEncryption passwordEncryption;


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
}