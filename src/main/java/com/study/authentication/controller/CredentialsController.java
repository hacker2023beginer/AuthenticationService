package com.study.authentication.controller;

import com.study.authentication.dto.CredentialsDto;
import com.study.authentication.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class CredentialsController {

    private final AuthService authService;

    public CredentialsController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/credentials")
    public void saveCredentials(@RequestBody CredentialsDto dto) {
        authService.saveCredentials(dto);
    }
}
