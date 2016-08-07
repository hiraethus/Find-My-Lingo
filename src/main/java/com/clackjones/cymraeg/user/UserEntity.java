package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "users")
public class UserEntity {
    @Id
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean isEnabled;

    @OneToMany
    @JoinColumn(name = "OWNER_USERNAME", referencedColumnName = "username")
    private Collection<GwasanaethEntity> gwasanaethau;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

