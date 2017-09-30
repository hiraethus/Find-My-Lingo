package com.clackjones.cymraeg.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {
    final static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private UserPassValidator userPassValidator;
    private UserDao userDao;
    private PasswordResetTokenDao tokenDao;
    private Sha256PasswordEncryption passwordEncryption;

    @Autowired
    public RegistrationService(
        JdbcUserDetailsManager jdbcUserDetailsManager,
        UserPassValidator userPassValidator,
        UserDao userDao,
        PasswordResetTokenDao tokenDao,
        Sha256PasswordEncryption passwordEncryption) {
            this.jdbcUserDetailsManager = jdbcUserDetailsManager;
            this.userPassValidator = userPassValidator;
            this.userDao = userDao;
            this.tokenDao = tokenDao;
            this.passwordEncryption = passwordEncryption;
    }


    public boolean register(RegistrationDetails details) throws RegistrationException {
        userPassValidator.validate(details);

        if (jdbcUserDetailsManager.userExists(details.getUsername())) {
            logger.info("Unable to register user as username, {}, already exists", details.getUsername());
            throw new RegistrationException("This user already exists", RegistrationExceptionType.USER_ALREADY_EXISTS);
        }

        passwordEncryption.encryptPassword(details);
        jdbcUserDetailsManager.createUser(user(details));
        jdbcUserDetailsManager.addUserToGroup(details.getUsername(), "end_users");

        logger.info("Registered user with username {}", details.getUsername());

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

    @Transactional
    public void resetPassword(RegistrationDetails registrationDetails, String token) throws RegistrationException{
        String email = registrationDetails.getUsername();
        if (!userExists(email)) {
            throw new RegistrationException("Could not find email", RegistrationExceptionType.INVALID_USERNAME_EMAIL);
        }

        boolean passwordsMatch = registrationDetails.getPassword()
                .equals(registrationDetails.getPasswordSecondTimeEntered());

        if (!passwordsMatch) {
            throw new RegistrationException("Passwords do not match", RegistrationExceptionType.UNMATCHED_PASSWORDS);
        }

        UserEntity userEntity = userDao.findById(registrationDetails.getUsername());


        PasswordResetTokenEntity tokenEntity;
        try {
            tokenEntity = tokenDao.findByEmailAndToken(userEntity, token);
        } catch (NoResultException exc) {
            tokenEntity = null;
        }

        if (tokenEntity == null || tokenEntity.isExpired()) {
            throw new RegistrationException("Your password reset token may have expired. Please try and reset your password again.",
                    RegistrationExceptionType.PASSWORD_TOKEN_EXPIRED);
        }

        passwordEncryption.encryptPassword(registrationDetails);
        userEntity.setPassword(registrationDetails.getPassword());
        userDao.merge(userEntity);

        tokenDao.remove(tokenEntity);
    }
}
