package com.provectus.budgetrush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Order;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByDateBetween(Date startDate, Date endDate);
}
