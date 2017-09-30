package com.clackjones.cymraeg.user;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDPasswordTokenGenerator implements PasswordTokenGenerator {
    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
