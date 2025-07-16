package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderDto {
    private Long orderId;
    private Long userId;
    private String userName; // optional
    private String menuName;
    private int price;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
