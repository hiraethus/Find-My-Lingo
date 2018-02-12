package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.Locale;

@Service
public class RegistrationService {
    final static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private UserPassValidator userPassValidator;
    private UserDao userDao;
    private PasswordResetTokenDao tokenDao;
    private EmailService emailService;
    private PasswordTokenGenerator tokenGenerator;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(
        JdbcUserDetailsManager jdbcUserDetailsManager,
        UserPassValidator userPassValidator,
        UserDao userDao,
        PasswordResetTokenDao tokenDao,
        EmailService emailService,
        PasswordTokenGenerator tokenGenerator) {
            this.jdbcUserDetailsManager = jdbcUserDetailsManager;
            this.userPassValidator = userPassValidator;
            this.userDao = userDao;
            this.tokenDao = tokenDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.emailService = emailService;
        this.tokenGenerator = tokenGenerator;
    }


    public boolean register(RegistrationDetails details, Locale locale) throws RegistrationException {
        userPassValidator.validate(details);

        if (jdbcUserDetailsManager.userExists(details.getUsername())) {
            logger.info("Unable to register user as username, {}, already exists", details.getUsername());
            throw new RegistrationException("This user already exists", RegistrationExceptionType.USER_ALREADY_EXISTS);
        }

        encryptPassword(details);
        jdbcUserDetailsManager.createUser(user(details));
        jdbcUserDetailsManager.addUserToGroup(details.getUsername(), "end_users");

        emailService.createAndSendRegistrationSuccessEmail(details, locale);
        logger.info("Registered user with username {}", details.getUsername());

        return true;
    }

    private void encryptPassword(RegistrationDetails regDetails) {
        regDetails.setPassword(passwordEncoder.encode(regDetails.getPassword()));
    }

    private User user(RegistrationDetails details) {
        return new User(details.getUsername(), details.getPassword(), Collections.emptyList());
    }

    public boolean userExists(String email) {
        return jdbcUserDetailsManager.userExists(email);
    }

    @Transactional
    public void sendResetToken(RegistrationDetails regDetails, Locale locale) {
        String email = regDetails.getUsername();
        logger.info("Sending password reset email to {}", email);

        UserEntity userEntity = userDao.findById(email);
        String token = tokenGenerator.generateToken();
        tokenDao.persist(new PasswordResetTokenEntity(token, userEntity));
        emailService.createAndSendResetTokenEmail(regDetails, token, locale);
    }

    @Transactional
    public void resetPassword(RegistrationDetails registrationDetails, String token) throws RegistrationException{
        String email = registrationDetails.getUsername();
        logger.info("Attempting to reset password for user {}", email);
        if (!userExists(email)) {
            logger.info("Could not reset {} password - user doesn't exist", email);
            throw new RegistrationException("Could not find email", RegistrationExceptionType.INVALID_USERNAME_EMAIL);
        }

        if (!registrationDetails.passwordsMatch()) {
            logger.info("Could not reset {} password - passwords don't match", email);
            throw new RegistrationException("Passwords do not match", RegistrationExceptionType.UNMATCHED_PASSWORDS);
        }

        UserEntity userEntity = userDao.findById(email);

        PasswordResetTokenEntity tokenEntity;
        try {
            tokenEntity = tokenDao.findByEmailAndToken(userEntity, token);
        } catch (NoResultException exc) {
            tokenEntity = null;
        }

        if (tokenEntity == null || tokenEntity.isExpired()) {
            logger.info("Could not reset {} password - passwords reset token doesn't exist", email);
            throw new RegistrationException("Your password reset token may have expired. Please try and reset your password again.",
                    RegistrationExceptionType.PASSWORD_TOKEN_EXPIRED);
        }

        encryptPassword(registrationDetails);
        userEntity.setPassword(registrationDetails.getPassword());
        userDao.merge(userEntity);

        tokenDao.remove(tokenEntity);
        logger.info("Reset {} password successfully", email);
    }
}
