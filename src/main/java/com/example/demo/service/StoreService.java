package com.example.demo.service;

import com.example.demo.domain.Menu;
import com.example.demo.domain.Store;
import com.example.demo.dto.MenuDto;
import com.example.demo.dto.StoreDto;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

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

    public List<StoreDto> findAll() {
        return storeRepository.findAll().stream()
                .map(store -> StoreDto.builder()
                        .id(store.getId())
                        .storeName(store.getStoreName())
                        .storeLocation(store.getStoreLocation())
                        .build())
                .collect(Collectors.toList());
    }

    public MenuDto addMenu(Long storeId, String menuName, Integer price, Integer amount, String description) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 store가 존재하지 않습니다."));

        Menu menu = Menu.builder()
                .store(store)
                .menuName(menuName)
                .price(price)
                .amount(amount)
                .description(description)
                .build();

        Menu saved = menuRepository.save(menu);

        return MenuDto.builder()
                .id(saved.getId())
                .storeId(store.getId())
                .menuName(saved.getMenuName())
                .price(saved.getPrice())
                .amount(saved.getAmount())
                .description(saved.getDescription())
                .createdAt(saved.getCreatedAt())
                .lastUpdatedAt(saved.getLastUpdatedAt())
                .build();
    }

    public void deleteMenu(Long storeId, String menuName) {
        Menu menu = menuRepository.findByStoreIdAndMenuName(storeId, menuName)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        menuRepository.delete(menu);
    }

    public List<MenuDto> getMenusByStoreId(Long storeId) {
        List<Menu> menus = menuRepository.findByStoreId(storeId);

        return menus.stream()
                .map(menu -> MenuDto.builder()
                        .id(menu.getId())
                        .menuName(menu.getMenuName())
                        .price(menu.getPrice())
                        .amount(menu.getAmount())
                        .description(menu.getDescription())
                        .createdAt(menu.getCreatedAt())
                        .lastUpdatedAt(menu.getLastUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}