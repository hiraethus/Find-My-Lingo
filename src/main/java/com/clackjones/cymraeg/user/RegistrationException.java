package com.clackjones.cymraeg.user;

public class RegistrationException extends Exception {
    private final RegistrationExceptionType kind;

    public RegistrationException(String message, RegistrationExceptionType kind) {
        super(message);
        this.kind = kind;
    }

    public RegistrationExceptionType getKind() {
        return kind;
    }
}
