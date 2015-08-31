package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.data.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
