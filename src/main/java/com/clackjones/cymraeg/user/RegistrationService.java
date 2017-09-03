package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Locale;
import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private UserPassValidator userPassValidator;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordResetTokenDao tokenDao;
    @Autowired
    private Sha256PasswordEncryption passwordEncryption;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MailSender mailSender;


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

    public void sendResetTokenEmail(RegistrationDetails registrationDetails, String resetToken, Locale locale) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registrationDetails.getUsername());

        String resetEmailContent = messageSource.getMessage("registration.reset.email",
                new String[]{resetToken}, locale);
        message.setText(resetEmailContent);
        String resetEmailSubject = messageSource.getMessage("registration.reset.email.subject",
                null, locale);
        message.setSubject(resetEmailSubject);

        mailSender.send(message);
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
        PasswordResetTokenEntity tokenEntity =
                tokenDao.findByEmailAndToken(userEntity, token);

        if (tokenEntity == null) {
            throw new RegistrationException("Invalid token, please try resetting your password again",
                    RegistrationExceptionType.INVALID_RESET_TOKEN);
        }

        passwordEncryption.encryptPassword(registrationDetails);
        userEntity.setPassword(registrationDetails.getPassword());
        userDao.merge(userEntity);

        // TODO delete token
    }
}
