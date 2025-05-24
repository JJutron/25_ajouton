package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserOrder;
import com.example.demo.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findByUserAndStatus(User user, OrderStatus status);


}
