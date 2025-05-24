package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private Long storeId;
    private int price;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}
