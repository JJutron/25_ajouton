package com.example.demo.controller;

import com.example.demo.dto.StoreDto;
import com.example.demo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}