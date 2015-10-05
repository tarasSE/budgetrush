package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @RequestMapping(value = "/{account_id}+{start_date}+{finish_date}", method = GET)
    @ResponseBody
    public List<OrderStatistic> getAmountMovements(@PathVariable Integer accountId,
                                                   @PathVariable String startDate,
                                                   @PathVariable String finishDate)  {

        Account account = new Account();
        account.setId(accountId);

        SimpleDateFormat format = new SimpleDateFormat();

        try {
            return service.getAmountMovementsByAccount(account, format.parse(startDate), format.parse(finishDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/income?{account_id}+{start_date}+{finish_date}", method = GET)
    @ResponseBody
    public List<OrderStatistic> getIncome(@PathVariable Integer accountId,
                                                   @PathVariable String startDate,
                                                   @PathVariable String finishDate)  {

        Account account = new Account();
        account.setId(accountId);

        SimpleDateFormat format = new SimpleDateFormat();

        try {
            return service.getIncomeByAccount(account, format.parse(startDate), format.parse(finishDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/expense?{account_id}+{start_date}+{finish_date}", method = GET)
    @ResponseBody
    public List<OrderStatistic> getExpense(@PathVariable Integer accountId,
                                          @PathVariable String startDate,
                                          @PathVariable String finishDate)  {

        Account account = new Account();
        account.setId(accountId);

        SimpleDateFormat format = new SimpleDateFormat();

        try {
            return service.getIncomeByAccount(account, format.parse(startDate), format.parse(finishDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {
        log.info("Create/update new order");
        service.create(order);
        return order;
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Order update(@RequestBody Order order, @PathVariable Integer id) {
        log.info("Create/update order id " + id);
        service.update(order, id);
        return order;
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@orderService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        service.delete(id);
    }
}
