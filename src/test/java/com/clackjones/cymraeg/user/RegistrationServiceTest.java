package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.email.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    @Mock
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Mock
    private UserPassValidator userPassValidator;

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordResetTokenDao passwordDao;
    @Mock
    private EmailService emailService;

    @Mock
    private PasswordTokenGenerator userIDProvider;

    @InjectMocks
    private RegistrationService registrationService;


    @Test
    public void shouldThrowExceptionWhenUserPassValidationFails() throws Exception {
        // given
        final String username = "someuser";
        RegistrationDetails regDetails = new RegistrationDetails();
        regDetails.setUsername(username);
        regDetails.setPassword("pass123");

        given(jdbcUserDetailsManager.userExists(username)).willReturn(false);
        given(userPassValidator.validate(regDetails)).willThrow(RegistrationException.class);

        // when
        RegistrationException r = null;
        try {
            registrationService.register(regDetails, Locale.ENGLISH);
        } catch (RegistrationException e) {
            r = e;
        }

        // then
        assertThat(r, notNullValue());
        then(jdbcUserDetailsManager).should(never()).createUser(any());
        then(jdbcUserDetailsManager).should(never()).addUserToGroup(username, "end_users");
    }

    @Test
    public void shouldThrowExceptionWhenUserExistsAlready() throws Exception {
        // given
        final String username = "someuser";
        RegistrationDetails regDetails = new RegistrationDetails();
        regDetails.setUsername(username);
        regDetails.setPassword("pass123");

        given(jdbcUserDetailsManager.userExists(username)).willReturn(true);

        // when
        RegistrationException r = null;
        try {
            registrationService.register(regDetails, Locale.ENGLISH);
        } catch (RegistrationException e) {
            r = e;
        }

        // then
        assertThat(r, notNullValue());
        assertThat(r.getKind(), is(RegistrationExceptionType.USER_ALREADY_EXISTS));
        then(jdbcUserDetailsManager).should(never()).createUser(any());
        then(jdbcUserDetailsManager).should(never()).addUserToGroup(username, "end_users");
    }

    @Test
    public void shouldRegisterSuccessfullyWhenUsernameValid() throws Exception {
        // given
        final String username = "someuser";
        RegistrationDetails regDetails = new RegistrationDetails();
        regDetails.setUsername(username);
        regDetails.setPassword("pass123");

        given(jdbcUserDetailsManager.userExists(username)).willReturn(false);

        // when
        boolean success = registrationService.register(regDetails, Locale.ENGLISH);

        // then
        then(jdbcUserDetailsManager).should(times(1)).createUser(any());
        then(jdbcUserDetailsManager).should(times(1)).addUserToGroup(username, "end_users");

        assertThat(success, is(true));
    }

    @Test
    public void shouldReturnTrueIfUserExistsInDB() {
        // given
        String username = "user@example.com";
        RegistrationDetails regDetails = new RegistrationDetails();
        regDetails.setUsername(username);

        given(jdbcUserDetailsManager.userExists(username)).willReturn(true);

        // then
        assertThat(registrationService.userExists(username), equalTo(true));
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExistInDB() {
        // given
        String username = "user@example.com";
        RegistrationDetails regDetails = new RegistrationDetails();
        regDetails.setUsername(username);

        given(jdbcUserDetailsManager.userExists(username)).willReturn(false);

        // then
        assertThat(registrationService.userExists(username), equalTo(false));
    }

    @Test
    public void shouldResetTokenAndPersistToDbAndSendEmail() {
        // given
        String email = "user@example.com";
        UserEntity user = new UserEntity();
        user.setEnabled(true);
        user.setUsername(email);

        RegistrationDetails details = new RegistrationDetails();
        details.setUsername(email);

        String uuid = "AAA111";

        given(userDao.findById(email)).willReturn(user);
        given(userIDProvider.generateToken()).willReturn(uuid);

        registrationService.sendResetToken(details, Locale.ENGLISH);

        // then
        verify(userDao, times(1)).findById(email);
        verify(passwordDao, times(1)).persist(any());
        verify(emailService, times(1)).createAndSendResetTokenEmail(details, uuid, Locale.ENGLISH);
    }
}
