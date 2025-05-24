package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private String storeName;
    private String menuName; // 단건 주문 가정 시
    private int price;
    private String status;
    private LocalDateTime createdAt;
}
