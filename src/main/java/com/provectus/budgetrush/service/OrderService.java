package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;

@Service
@Transactional
public class OrderService extends GenericService<Order, OrderRepository> {

    @Qualifier("orderRepository")
    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected OrderRepository getRepository() {
        return orderRepository;
    }
}
