package com.example.demo.repository;

import com.example.demo.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByStoreIdAndMenuName(Long storeId, String menuName);
    List<Menu> findByStoreId(Long storeId);
    Optional<Menu> findFirstByStoreId(Long storeId);

}
