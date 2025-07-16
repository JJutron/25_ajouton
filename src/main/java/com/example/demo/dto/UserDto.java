package com.example.demo.dto;

import com.example.demo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole role;
    private String studentNumber;     // 학번
    private String department;        // 학과
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

}
