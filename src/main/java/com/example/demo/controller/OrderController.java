package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestParam Long userId,
                                                @RequestParam Long storeId,
                                                @RequestParam String menuName) {
        return ResponseEntity.ok(orderService.createOrder(userId, storeId, menuName));
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeOrder(@RequestParam Long userId,
                                                @RequestParam Long orderId) {
        orderService.completeOrder(userId, orderId);
        return ResponseEntity.ok("주문이 완료 처리되었습니다.");
    }

    @GetMapping("/is-completed")
    public ResponseEntity<Boolean> isOrderCompleted(@RequestParam Long orderId) {
        boolean result = orderService.isOrderCompleted(orderId);
        return ResponseEntity.ok(result);
    }
}