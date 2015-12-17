package com.provectus.budgetrush.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.FilterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;

@Service
@Repository
@Transactional(readOnly = true)
public class OrderService extends GenericService<Order, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Override
    protected OrderRepository getRepository() {
        return orderRepository;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        Order newOrder = super.create(order);
        accountService.increaseBalance(newOrder.getAccount(), newOrder.getAmount());
        return newOrder;
    }

    @Override
    @Transactional
    public Order update(Order order, int id) {
        Order oldOrder = getById(id);
        accountService.decreaseBalance(oldOrder.getAccount(), oldOrder.getAmount());

        Order newOrder = super.update(order, id);
        accountService.increaseBalance(newOrder.getAccount(), newOrder.getAmount());

        return newOrder;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Order oldOrder = getById(id);
        accountService.decreaseBalance(oldOrder.getAccount(), oldOrder.getAmount());

        return super.delete(id);

    }

    public List<Order> getOrdersByPeriod(Date startDate,
                                         Date endDate) {
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        return getRepository().findByDateBetween(startDate, endDate);
    }

    public List<Order> getOrdersByPeriodAndAccountAndFilter(Account account,
                                                            Date startDate,
                                                            Date endDate,
                                                            FilterEnum filter) {

        Assert.notNull(account, "Account can't be null");
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        if (filter == FilterEnum.EXPENSE) {
            return getRepository()
                    .findByAccountAndDateBetweenAndAmountIsLessThan(
                            account,
                            startDate,
                            endDate,
                            BigDecimal.ZERO);
        }
        if (filter == FilterEnum.INCOME) {
            return getRepository()
                    .findByAccountAndDateBetweenAndAmountIsGreaterThanEqual(
                            account,
                            startDate,
                            endDate,
                            BigDecimal.ZERO);
        }

        return getRepository().findByAccountAndDateBetween(account, startDate, endDate);
    }

}
