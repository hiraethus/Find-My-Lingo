package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth;

import java.util.Collection;

public class User {
    private String username;
    private boolean isEnabled;
    private Collection<Gwasanaeth> gwasanaethau;
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Collection<Gwasanaeth> getGwasanaethau() {
        return gwasanaethau;
    }

    public void setGwasanaethau(Collection<Gwasanaeth> gwasanaethau) {
        this.gwasanaethau = gwasanaethau;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
