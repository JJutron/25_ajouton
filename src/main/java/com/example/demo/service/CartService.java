package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartItem;
import com.example.demo.domain.User;
import com.example.demo.dto.CartDto;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public CartDto createCartForUser(Long userId) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 이미 장바구니가 존재하는 경우
        Optional<Cart> existingCart = cartRepository.findByUser(user);
        if (existingCart.isPresent()) {
            return convertToDto(existingCart.get());
        }

        // 새 장바구니 생성
        Cart newCart = Cart.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        Cart savedCart = cartRepository.save(newCart);

        return convertToDto(savedCart);
    }

    private CartDto convertToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .createdAt(cart.getCreatedAt())
                .build();
    }

    public CartDto getCartByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 장바구니가 존재하지 않습니다."));

        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .createdAt(cart.getCreatedAt())
                .lastUpdatedAt(cart.getLastUpdatedAt())
                .build();
    }

    // 장바구니 삭제
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new IllegalArgumentException("해당 장바구니가 존재하지 않습니다.");
        }
        cartRepository.deleteById(cartId);
    }

    public void deleteCartItem(Long cartId, Long menuId) {
        CartItem cartItem = cartItemRepository.findByCartIdAndMenuId(cartId, menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니 항목이 존재하지 않습니다."));
        cartItemRepository.delete(cartItem);
    }
}
