package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.data.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
