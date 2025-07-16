package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private Long id;
    private String storeName;
    private String storeLocation;
}