package com.clackjones.cymraeg.registration;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class UserPassValidatorTest {

    @Test
    public void shouldThrowExceptionWhenPasswordsDontMatch() {
        // given
        UserPassValidator validator = new UserPassValidator();
        RegistrationDetails details = new RegistrationDetails();
        details.setUsername("user");
        details.setPassword("password1");
        details.setPasswordSecondTimeEntered("password1_unmatched");

        // when
        RegistrationException r = null;
        try {
            validator.validate(details);
        } catch (RegistrationException e) {
            r = e;
        }

        // then
        assertThat(r, notNullValue());
        assertThat(r.getKind(), is(RegistrationExceptionType.UNMATCHED_PASSWORDS));
        assertThat(r.getMessage(), equalTo("Passwords do not match"));
    }

    @Test
    public void shouldThrowExceptionWhenUsernameTooLong() {
        // given
        UserPassValidator validator = new UserPassValidator();
        RegistrationDetails details = new RegistrationDetails();

        int tooLongLength = UserPassValidator.MAX_USER_LENGTH + 1;
        char[] tooLongUser = new char[tooLongLength];
        Arrays.fill(tooLongUser, 'a');
        String username = new String(tooLongUser);

        details.setUsername(username);
        details.setPassword("password1");
        details.setPasswordSecondTimeEntered("password1");

        // when
        RegistrationException r = null;
        try {
            validator.validate(details);
        } catch (RegistrationException e) {
            r = e;
        }

        // then
        assertThat(r, notNullValue());
        assertThat(r.getKind(), is(RegistrationExceptionType.USER_TOO_LONG));
        assertThat(r.getMessage(), equalTo("Username is too long. Must be shorter than " + UserPassValidator.MAX_USER_LENGTH));
    }

    @Test
    public void shouldThrowExceptionWhenPasswordTooShort() {
        // given
        UserPassValidator validator = new UserPassValidator();
        RegistrationDetails details = new RegistrationDetails();

        int tooShortLength = UserPassValidator.MIN_PASSWORD_LENGTH - 1;
        char[] tooShortPass = new char[tooShortLength];
        Arrays.fill(tooShortPass, 'a');
        String password = new String(tooShortPass);

        details.setUsername("username");
        details.setPassword(password);
        details.setPasswordSecondTimeEntered(password);

        // when
        RegistrationException r = null;
        try {
            validator.validate(details);
        } catch (RegistrationException e) {
            r = e;
        }

        // then
        assertThat(r, notNullValue());
        assertThat(r.getKind(), is(RegistrationExceptionType.PASS_TOO_SHORT));
        assertThat(r.getMessage(), equalTo("Password is too short. Must be longer than " + UserPassValidator.MIN_PASSWORD_LENGTH));
    }

    @Test
    public void shouldReturnTrueWhenCriteriaSatisfied() throws Exception {
        // given
        UserPassValidator validator = new UserPassValidator();
        RegistrationDetails details = new RegistrationDetails();

        int permissibleLengthUser = UserPassValidator.MAX_USER_LENGTH;
        char[] rightLengthUser = new char[permissibleLengthUser];
        Arrays.fill(rightLengthUser, 'a');
        String username = new String(rightLengthUser);

        int tooShortLengthPass = UserPassValidator.MIN_PASSWORD_LENGTH;
        char[] rightLengthPass = new char[tooShortLengthPass];
        Arrays.fill(rightLengthPass, 'a');
        String password = new String(rightLengthPass);

        details.setUsername(username);
        details.setPassword(password);
        details.setPasswordSecondTimeEntered(password);

        // then
        assertThat(validator.validate(details), is(true));
    }
}
