package com.study.authentication.controller;

import com.study.authentication.entity.Role;
import com.study.authentication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(@RequestParam String login,
                           @RequestParam String password,
                           @RequestParam Role role) {
        userService.registerUser(login, password, role);
        return "User registered";
    }
}

