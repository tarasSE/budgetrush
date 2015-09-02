package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class OrderService extends GenericService <Order, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    protected OrderRepository getRepository() {
        return orderRepository;
    }
}
