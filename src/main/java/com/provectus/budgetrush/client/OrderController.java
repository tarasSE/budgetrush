package com.provectus.budgetrush.client;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.service.OrderService;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping
@RestController
@Serialization
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
