package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}