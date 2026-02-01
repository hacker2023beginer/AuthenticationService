package com.study.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class MyController {
    @GetMapping
    public String getAllUsers() {
        return "Good start";
    }
}
