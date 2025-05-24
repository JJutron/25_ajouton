package com.example.demo.repository;

public interface UserRepository {
    boolean existsByEmail(String email);
}
