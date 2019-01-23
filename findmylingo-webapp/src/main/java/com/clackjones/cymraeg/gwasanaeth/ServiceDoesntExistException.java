package com.clackjones.cymraeg.gwasanaeth;

public class ServiceDoesntExistException extends Exception {
    public ServiceDoesntExistException(String msg) {
        super(msg);
    }
}
