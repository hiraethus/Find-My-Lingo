package com.clackjones.cymraeg.email;

import com.clackjones.cymraeg.user.RegistrationDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
    @Mock
    private MessageSource messageSource;
    @Mock
    private MailSender mailSender;
    @InjectMocks
    private EmailService emailService;

    @Test
    public void shouldCreateResetTokenEmail() {
        // given
        RegistrationDetails details = new RegistrationDetails();
        details.setUsername("joe.bloggs@example.com");
        String resetToken = "11246j2ojnv";
        Locale locale = Locale.ENGLISH;

        given(messageSource.getMessage("registration.reset.email.subject",
                null, locale)).willReturn("Message Subject");
        given(messageSource.getMessage("registration.reset.email",
                new String[]{resetToken}, locale)).willReturn("Message Content");


        // when
        SimpleMailMessage result = emailService.createResetTokenEmail(details, resetToken, locale);

        // then
        assertThat(result.getTo()[0], equalTo("joe.bloggs@example.com"));
        assertThat(result.getSubject(), equalTo("Message Subject"));
        assertThat(result.getText(), equalTo("Message Content"));
    }

    @Test
    public void shouldCreateAndSendResetTokenEmail() {
        // given
        EmailService emailServiceSpy = spy(emailService);
        RegistrationDetails details = new RegistrationDetails();
        String token = "EF12463F";
        Locale locale = Locale.ENGLISH;
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        doReturn(mailMessage).when(emailServiceSpy).createResetTokenEmail(details, token, locale);

        // when
        emailServiceSpy.createAndSendResetTokenEmail(details, token, locale);

        // then
        verify(mailSender).send(mailMessage);
        verify(emailServiceSpy)
                .createResetTokenEmail(details, token, locale);
    }
}
