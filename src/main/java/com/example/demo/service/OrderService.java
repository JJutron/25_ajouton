package com.example.demo.service;

import com.example.demo.domain.Menu;
import com.example.demo.domain.Store;
import com.example.demo.domain.User;
import com.example.demo.domain.UserOrder;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderResponseDto;
import com.example.demo.dto.StoreOrderDto;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.UserRole;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createOrder(Long userId, Long storeId, String menuName) {

        // 1. 사용자 유효성 검사
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 2. 상점 유효성 검사
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        // 3. 메뉴 검색
        Menu menu = menuRepository.findByStoreIdAndMenuName(storeId, menuName)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게에 해당 이름의 메뉴가 존재하지 않습니다."));

        // 4. 주문 생성
        UserOrder order = UserOrder.builder()
                .user(user)
                .store(store)
                .price(menu.getPrice()) // 메뉴 가격 사용
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .build();

        UserOrder saved = orderRepository.save(order);

        // 5. DTO 반환
        return OrderDto.builder()
                .id(saved.getId())
                .userId(user.getId())
                .storeId(store.getId())
                .price(saved.getPrice())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .lastUpdatedAt(saved.getLastUpdatedAt())
                .build();
    }

    @Transactional
    public void completeOrder(Long userId, Long orderId) {
        // 1. 사용자 존재 여부 및 권한 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        // 2. 주문 존재 여부 확인 및 상태 변경
        UserOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        order.setStatus(OrderStatus.COMPLETED);
        order.setLastUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Transactional()
    public boolean isOrderCompleted(Long orderId) {
        UserOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        return order.getStatus() == OrderStatus.COMPLETED;
    }

    @Transactional
    public List<OrderDto> getPendingOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        List<UserOrder> pendingOrders = orderRepository.findByUserAndStatus(user, OrderStatus.PENDING);


        return pendingOrders.stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .userId(order.getUser().getId())
                        .storeId(order.getStore().getId())
                        .price(order.getPrice())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .lastUpdatedAt(order.getLastUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderResponseDto> getUserOrdersWithDetails(Long userId) {
        List<UserOrder> orders = orderRepository.findByUserId(userId);

        return orders.stream().map(order -> {
            String storeName = order.getStore().getStoreName();
            Optional<Menu> firstMenu = menuRepository.findFirstByStoreId(order.getStore().getId());

            String menuName = firstMenu.map(Menu::getMenuName).orElse("메뉴 정보 없음");
            return OrderResponseDto.builder()
                    .id(order.getId())
                    .userId(order.getUser().getId())
                    .storeName(storeName)
                    .menuName(menuName)
                    .price(order.getPrice())
                    .status(order.getStatus().name()) // Enum -> String
                    .createdAt(order.getCreatedAt())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<StoreOrderDto> getOrdersByStoreId(Long storeId) {
        List<UserOrder> orders = orderRepository.findByStoreId(storeId);

        return orders.stream().map(order -> {
            String menuName = menuRepository
                    .findFirstByStoreIdAndPrice(order.getStore().getId(), order.getPrice())
                    .map(Menu::getMenuName)
                    .orElse("알 수 없음");

            return StoreOrderDto.builder()
                    .orderId(order.getId())
                    .userId(order.getUser().getId())
                    .userName(order.getUser().getName()) // 필요시
                    .menuName(menuName)
                    .price(order.getPrice())
                    .status(order.getStatus())
                    .createdAt(order.getCreatedAt())
                    .build();
        }).collect(Collectors.toList());
    }
}
