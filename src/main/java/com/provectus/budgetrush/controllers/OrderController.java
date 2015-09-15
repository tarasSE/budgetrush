package com.provectus.budgetrush.controllers;

import java.util.List;

import com.provectus.budgetrush.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.service.OrderService;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/v1/orders", method = GET)
    @ResponseBody
    public List<Order> listAll() {
        log.info("Get all orders");
        return service.getAll();
    }

    @RequestMapping(method = GET, value = "/v1/orders/{id}")
    @ResponseBody
    public Order getById(@PathVariable Integer id) {
        log.info("Get order by id" + id);
        return service.getById(id);
    }

    @RequestMapping(value = "/v1/orders", method = POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {
        log.info("Create/update new order");
        service.createOrUpdate(order);
        return order;
    }

    @RequestMapping(value = "/v1/orders/{id}", method = PUT)
    @ResponseBody
    public Order update(@RequestBody Order order, @PathVariable Integer id) {
        log.info("Create/update order id " + id);
        order.setId(id);
        service.createOrUpdate(order);
        return order;
    }

    @RequestMapping(value = "/v1/orders/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        service.delete(id);
    }
}
