package com.provectus.budgetrush.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.OrderRepository;

@Service
@Transactional
public class OrderService extends GenericService<Order, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected OrderRepository getRepository() {
        return orderRepository;
    }

    public List<OrderStatistic> getAmountMovementsByAccount(Account account, Date startDate, Date endDate) {
        return getRepository().getAmountMovementsByAccount(account, startDate, endDate);
    }
}
