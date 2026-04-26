package com.study.authentication.controller;

import com.study.authentication.dto.*;
import com.study.authentication.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request.getLogin(), request.getPassword());
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/refresh")
    public LoginResponseDto refresh(@RequestBody RefreshRequestDto request) {
        return authService.refreshToken(request.getRefreshToken());
    }
}
