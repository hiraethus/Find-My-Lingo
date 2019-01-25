package com.clackjones.cymraeg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class UserPassValidator {
    public static final int MAX_USER_LENGTH = 50;
    public static final int MIN_USER_LENGTH = 6;
    public static final int MIN_PASSWORD_LENGTH = 8;

    @Autowired
    private EmailValidator emailValidator;

    public boolean validate(RegistrationDetails details) throws RegistrationException {
        if (!passwordsMatch(details)) {
            throw new RegistrationException("Passwords do not match",
                    RegistrationExceptionType.UNMATCHED_PASSWORDS);
        }

        if (details.getUsername().length() > MAX_USER_LENGTH) {
            throw new RegistrationException("Username is too long. Must be shorter than " + UserPassValidator.MAX_USER_LENGTH,
                    RegistrationExceptionType.USER_TOO_LONG);
        }

        if (details.getUsername().length() < MIN_USER_LENGTH) {
            throw new RegistrationException("Username is too short. Must be longer than " + UserPassValidator.MIN_USER_LENGTH,
                    RegistrationExceptionType.USER_TOO_SHORT);
        }

        if (details.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Password is too short. Must be longer than " + UserPassValidator.MIN_PASSWORD_LENGTH,
                RegistrationExceptionType.PASS_TOO_SHORT);
        }

        if ( !emailValidator.isEmailValid(details.getUsername())) {
            throw new RegistrationException("Username is invalid email",
                    RegistrationExceptionType.INVALID_USERNAME_EMAIL);
        }

        return true;
    }

    private boolean passwordsMatch(RegistrationDetails details) {
        return details.getPassword().equals(details.getPasswordSecondTimeEntered());
    }
}
