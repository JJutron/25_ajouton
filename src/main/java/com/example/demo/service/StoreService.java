package com.example.demo.service;

import com.example.demo.domain.Store;
import com.example.demo.dto.StoreDto;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreDto save(String storeName, String storeLocation) {
        Store store = Store.builder()
                .storeName(storeName)
                .storeLocation(storeLocation)
                .build();

        store = storeRepository.save(store); // 실제 DB 저장

        return StoreDto.builder()
                .id(store.getId()) // 저장된 ID
                .storeName(store.getStoreName())
                .storeLocation(store.getStoreLocation())
                .build();
    }
}