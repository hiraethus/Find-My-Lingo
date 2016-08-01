package com.clackjones.cymraeg.registration;

import org.springframework.stereotype.Component;

@Component
class UserPassValidator {
    public static final int MAX_USER_LENGTH = 50;
    public static final int MIN_USER_LENGTH = 6;
    public static final int MIN_PASSWORD_LENGTH = 8;

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

        return true;
    }

    private boolean passwordsMatch(RegistrationDetails details) {
        return details.getPassword().equals(details.getPasswordSecondTimeEntered());
    }
}
