package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Long id;
    private Long cartId;
    private Long menuId;
    private int amount;
    private String menuName;
    private int price;
}