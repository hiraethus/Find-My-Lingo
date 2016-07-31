package com.clackjones.cymraeg.registration;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    @Mock
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Mock
    private UserPassValidator userPassValidator;

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
            registrationService.register(regDetails);
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
            registrationService.register(regDetails);
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
        boolean success = registrationService.register(regDetails);


        // then
        then(jdbcUserDetailsManager).should(times(1)).createUser(any());
        then(jdbcUserDetailsManager).should(times(1)).addUserToGroup(username, "end_users");

        assertThat(success, is(true));
    }
}
