package com.provectus.budgetrush.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.repository.OrderRepository;

@Service
@Transactional
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
        accountService.incressBalance(newOrder.getAccount(), newOrder.getAmount());
        return newOrder;
    }

    @Override
    @Transactional
    public Order update(Order order, int id) {
        Order oldOrder = getById(id);
        accountService.decreaseBalance(oldOrder.getAccount(), oldOrder.getAmount());

        Order newOrder = super.update(order, id);
        accountService.incressBalance(newOrder.getAccount(), newOrder.getAmount());

        return newOrder;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Order oldOrder = getById(id);
        accountService.decreaseBalance(oldOrder.getAccount(), oldOrder.getAmount());

        return super.delete(id);

    }

    @Transactional
    public List<OrderStatistic> getTurnoverByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getTurnoverByAccount(accountId, startDate, endDate);
    }

    @Transactional
    public List<OrderStatistic> getIncomeByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getIncomeByAccount(accountId, startDate, endDate);
    }

    @Transactional
    public List<OrderStatistic> getExpenseByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getExpenseByAccount(accountId, startDate, endDate);
    }

    @Transactional
    public List<OrderStatistic> getExpenseByCategory(Category category, Date startDate, Date endDate) {
        return getRepository().getExpenseByCategory(category, startDate, endDate);
    }

}
