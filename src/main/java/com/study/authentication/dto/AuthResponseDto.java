package com.study.authentication.dto;

public class AuthResponseDto {
    private Long userId;
    private String login;

    public AuthResponseDto(Long userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
