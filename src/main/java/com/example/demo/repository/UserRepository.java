package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<com.example.entity.User, Long> {

    // 이메일로 User 조회
    Optional<com.example.entity.User> findByEmail(String email);

    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);
}
