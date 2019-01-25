package com.clackjones.cymraeg.user;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    private final Pattern emailPattern = Pattern.compile("^\\S+@\\S+$");
    public boolean isEmailValid(String emailAddress) {
        return emailPattern.matcher(emailAddress).matches();
    }
}
