package com.example.demo.repository;

import com.example.demo.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndMenuId(Long cartId, Long menuId);
    void deleteByCartIdAndMenuId(Long cartId, Long menuId);
}
