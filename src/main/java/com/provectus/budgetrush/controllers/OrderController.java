package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.FilterEnum;
import com.provectus.budgetrush.data.PeriodsEnum;
import com.provectus.budgetrush.data.order.Order;
import com.provectus.budgetrush.dateprocessor.DateProcessor;
import com.provectus.budgetrush.dateprocessor.Period;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(value = "/v1/orders", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class OrderController {

    @Inject
    private OrderService orderService;

    @Inject
    private AccountService accountService;

    @Inject
    private GroupService groupService;

    @Inject
    private DateProcessor dateProcessor;

    @PostFilter("inGroupOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Order> listAll(
            @RequestParam(required = false) PeriodsEnum period,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer accountId,
            @RequestParam(required = false) FilterEnum filter) {

        if (period == null && accountId == null) {
            log.info("Get all orders");
            return orderService.getAll();
        }

        Period createdPeriod = dateProcessor.createPeriod(period, startDate,
                endDate);

        if (period != null && accountId == null) {

            log.info("Get orders by period.");

            return orderService.getOrdersByPeriod(
                    createdPeriod.getStartDate(),
                    createdPeriod.getEndDate());
        }

        return orderService
                .getOrdersByPeriodAndAccountAndFilter(
                        accountService.getById(accountId),
                        createdPeriod.getStartDate(),
                        createdPeriod.getEndDate(),
                        filter);

    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Order getById(@PathVariable Integer id) {
        log.info("Get order by id" + id);
        return orderService.getById(id);
    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(" +
            "@accountService.getById(" +
            "#order.getAccount().getId()" +
            ").getGroup().getId()), 'write')") // TODO more compact
    @RequestMapping(method = POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {
        log.info("Create new order");

        return orderService.create(order);
    }

    @PreAuthorize("inGroupOrAdmin(@orderService.getById(#id), 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Order update(@RequestBody Order order, @PathVariable Integer id) {
        log.info("Update order id " + id);
        return orderService.update(order, id);
    }

    @PreAuthorize("inGroupOrAdmin(@orderService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        orderService.delete(id);
    }
}
