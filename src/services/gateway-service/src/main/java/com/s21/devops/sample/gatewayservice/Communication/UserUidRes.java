package com.s21.devops.sample.gatewayservice.Communication;

import java.util.UUID;

public class UserUidRes {
    private UUID userUid;
    private String username;
    private String password;
    private String role;

    // Геттеры и сеттеры
    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}