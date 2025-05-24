package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.loginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class loginController {

    private final loginService loginService;

    @PostMapping("/signup")
    public UserDto signup(@RequestParam String email,
                          @RequestParam String name,
                          @RequestParam String role,
                          @RequestParam String password,
                          @RequestParam String stdNum,
                          @RequestParam String depart) {
        return loginService.signup(email, name, role, password, stdNum, depart);
    }
}
