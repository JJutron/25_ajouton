package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    // 1. 장바구니 생성
    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(@RequestParam Long userId) {
        CartDto cartDto = cartService.createCartForUser(userId);
        return ResponseEntity.ok(cartDto);
    }

    // 2. 사용자별 장바구니 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUser(@PathVariable Long userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }

    // 3. 장바구니 삭제
    @DeleteMapping("/delete-all/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("장바구니가 삭제되었습니다.");
    }

    @DeleteMapping("/{cartId}/item/{menuId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartId,
                                                 @PathVariable Long menuId) {
        cartService.deleteCartItem(cartId, menuId);
        return ResponseEntity.ok("해당 항목이 장바구니에서 삭제되었습니다.");
    }
}
