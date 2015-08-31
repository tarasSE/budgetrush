package com.provectus.budgetrush.service;

import java.util.List;

import com.provectus.budgetrush.data.Order;

public interface OrderService {

    Order addOrder(Order order);

    void delete(int id);

    Order getByID(int id);

    Order editOrder(Order order);

    List<Order> getAll();

}
