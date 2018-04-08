package com.clackjones.cymraeg.user;

public class RegistrationDetails {
    private String username;
    private String nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean passwordsMatch() {
        return password.equals(passwordSecondTimeEntered);
    }
}
