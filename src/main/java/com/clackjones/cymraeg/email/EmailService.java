package com.clackjones.cymraeg.email;

import com.clackjones.cymraeg.user.RegistrationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EmailService {
    static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private MailSender mailSender;
    private MessageSource messageSource;

    @Autowired
    public EmailService(MailSender mailSender, MessageSource messageSource) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
    }

    public void createAndSendResetTokenEmail(RegistrationDetails registrationDetails, String resetToken, Locale locale) {
        logger.info("Sending Email Reset Token to user {} with token {}",
                registrationDetails.getUsername(), resetToken);
        SimpleMailMessage message = createResetTokenEmail(registrationDetails, resetToken, locale);
        mailSender.send(message);
    }

    SimpleMailMessage createResetTokenEmail(RegistrationDetails registrationDetails, String resetToken, Locale locale) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registrationDetails.getUsername());

        String resetEmailContent = messageSource.getMessage("registration.reset.email",
                new String[]{resetToken}, locale);
        message.setText(resetEmailContent);
        String resetEmailSubject = messageSource.getMessage("registration.reset.email.subject",
                null, locale);
        message.setSubject(resetEmailSubject);

        return message;
    }
}
