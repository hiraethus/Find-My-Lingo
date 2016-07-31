package com.clackjones.cymraeg.registration;

public class RegistrationDetails {
    private String username;
    private String password;
    private String passwordSecondTimeEntered;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSecondTimeEntered() {
        return passwordSecondTimeEntered;
    }

    public void setPasswordSecondTimeEntered(String passwordSecondTimeEntered) {
        this.passwordSecondTimeEntered = passwordSecondTimeEntered;
    }
}
