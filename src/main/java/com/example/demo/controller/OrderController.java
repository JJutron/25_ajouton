package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserOrder;
import com.example.demo.dto.OrderDto;
import com.example.demo.enums.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getPendingOrders(@RequestParam Long userId) {
        List<OrderDto> orders = orderService.getPendingOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
}