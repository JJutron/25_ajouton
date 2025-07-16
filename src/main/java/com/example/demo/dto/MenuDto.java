package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MenuDto {

    private Long id;
    private Long storeId;
    private String menuName;
    private Integer price;
    private Integer amount;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}