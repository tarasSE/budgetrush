package com.provectus.budgetrush.client;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "/order", method = GET)
    @ResponseBody
    public List<Order> listAll() {
        return service.getAll();
    }

    @RequestMapping(method = GET, value = "/order/{id}")
    @ResponseBody
    public Order getById(@PathVariable Integer id) {

        return service.getById(id);
    }

    @RequestMapping(value = "/order", method = POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {
        service.createAndUpdate(order);
        return order;
    }

    @RequestMapping(value = "order/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
