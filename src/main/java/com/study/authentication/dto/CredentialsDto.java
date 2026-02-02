package com.study.authentication.dto;

import com.study.authentication.entity.Role;

public class CredentialsDto {
    private Long userId;
    private String login;
    private String password;
    private Role role;

    public CredentialsDto() {
    }

    public CredentialsDto(Long userId, String login, String password, Role role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
