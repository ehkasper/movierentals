package com.movierental.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    private boolean enabled;

    public User() {}

    public User(String username, String password, boolean enabled) {
        this.enabled = enabled;
        this.username = username;
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
