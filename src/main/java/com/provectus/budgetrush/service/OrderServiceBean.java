package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceBean implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order addOrder(Order order) {
        log.info("Start to save order (ID=" + order.getId()+")");
        return orderRepository.saveAndFlush(order);
    }


    public void delete(int id) {
        log.info("Start to delete order (ID=" + id + ")");
        orderRepository.delete(id);
    }


    public Order getByID(int id) {
        return orderRepository.getOne(id);
    }


    public Order editOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }


    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
