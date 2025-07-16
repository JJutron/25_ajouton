package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.loginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            boolean valid = loginService.login(email, password);

            if (!valid) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }

            UserDto userDto = loginService.getUserDtoByEmail(email);
            return ResponseEntity.ok(userDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return loginService.getAllUsers();
    }

    @GetMapping("/me")
    public UserDto getMe(@RequestParam String email) {
        return loginService.getUserByEmail(email);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        loginService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
