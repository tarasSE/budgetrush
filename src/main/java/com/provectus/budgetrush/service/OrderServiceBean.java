package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;

public class OrderServiceBean implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    @Override
    public Order getByID(int id) {
        return orderRepository.getOne(id);
    }

    @Override
    public Order editOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
