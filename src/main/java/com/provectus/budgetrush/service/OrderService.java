package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService extends GenericService<Order, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected OrderRepository getRepository() {
        return orderRepository;
    }

    public List<OrderStatistic> getTurnoverByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getTurnoverByAccount(accountId, startDate, endDate);
    }

    public List<OrderStatistic> getIncomeByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getIncomeByAccount(accountId, startDate, endDate);
    }

    public List<OrderStatistic> getExpenseByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getExpenseByAccount(accountId, startDate, endDate);
    }
}
