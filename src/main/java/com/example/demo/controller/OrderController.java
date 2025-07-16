package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserOrder;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderResponseDto;
import com.example.demo.dto.StoreOrderDto;
import com.example.demo.enums.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/all_detail")
    public ResponseEntity<List<OrderResponseDto>> getUserOrdersWithDetails(@RequestParam Long userId) {
        List<OrderResponseDto> orders = orderService.getUserOrdersWithDetails(userId);
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "가게별 오더", description = "userId와 storeId를 기반으로 주문 목록 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/store_order")
    public ResponseEntity<List<StoreOrderDto>> getOrdersByStoreId(
            @RequestParam Long storeId
    ) {
        return ResponseEntity.ok(orderService.getOrdersByStoreId(storeId));
    }
}