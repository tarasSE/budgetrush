package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    void delete(int id);

    Order getByID(int id);

    Order editOrder(Order order);

    List<Order> getAll();

}
