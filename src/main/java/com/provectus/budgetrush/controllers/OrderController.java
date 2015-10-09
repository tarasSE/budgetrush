package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(value = "/v1/orders", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Order> listAll() {
        log.info("Get all orders");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Order getById(@PathVariable Integer id) {
        log.info("Get order by id" + id);
        return service.getById(id);
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "statistics/turnover", method = GET)
    @ResponseBody
    public List<OrderStatistic> getTurnover(@RequestParam int accountId,
                                            @RequestParam long startDate,
                                            @RequestParam long finishDate) {

        return service.getTurnoverByAccount(accountId, new Date(startDate), new Date(finishDate));

    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "statistics/income", method = GET)
    @ResponseBody
    public List<OrderStatistic> getIncome(@RequestParam int accountId,
                                          @RequestParam long startDate,
                                          @RequestParam long finishDate) {

        return service.getIncomeByAccount(accountId, new Date(startDate), new Date(finishDate));
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "statistics/expense", method = GET)
    @ResponseBody
    public List<OrderStatistic> getExpense(@RequestParam int accountId,
                                           @RequestParam long startDate,
                                           @RequestParam long finishDate) {

        return service.getExpenseByAccount(accountId, new Date(startDate), new Date(finishDate));
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {
        log.info("Create/update new order");

        return service.create(order);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Order update(@RequestBody Order order, @PathVariable Integer id) {
        log.info("Create/update order id " + id);
        return  service.update(order, id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@orderService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        service.delete(id);
    }
}
