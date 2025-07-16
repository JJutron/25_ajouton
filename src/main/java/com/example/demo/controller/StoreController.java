package com.example.demo.controller;

import com.example.demo.dto.MenuDto;
import com.example.demo.dto.StoreDto;
import com.example.demo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/save")
    public ResponseEntity<StoreDto> saveStore(@RequestParam String storeName,
                                              @RequestParam String storeLocation) {
        StoreDto result = storeService.save(storeName, storeLocation);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> stores = storeService.findAll();
        return ResponseEntity.ok(stores);
    }


    @PostMapping("/{storeId}/menu")
    public ResponseEntity<MenuDto> addMenu(@PathVariable Long storeId,
                                           @RequestParam String menuName,
                                           @RequestParam int price,
                                           @RequestParam(required = false, defaultValue = "0") int amount,
                                           @RequestParam String description) {
        MenuDto menu = storeService.addMenu(storeId, menuName, price, amount, description);
        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{storeId}/menu")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long storeId,
                                           @RequestParam String menuName) {
        storeService.deleteMenu(storeId, menuName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{storeId}/menus")
    public ResponseEntity<List<MenuDto>> getMenusByStoreId(@PathVariable Long storeId) {
        List<MenuDto> menus = storeService.getMenusByStoreId(storeId);
        return ResponseEntity.ok(menus);
    }

}