package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Periods;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.dateproc.DateProcessorBean;
import com.provectus.budgetrush.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Repository
@Transactional(readOnly = true)
public class OrderService extends GenericService<Order, OrderRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DateProcessorBean dateProcessor;

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

    public List<OrderStatistic> getTurnoverByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getTurnoverByAccount(accountId, startDate, endDate);
    }

    public List<OrderStatistic> getIncomeByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getIncomeByAccount(accountId, startDate, endDate);
    }

    public List<OrderStatistic> getExpenseByAccount(int accountId, Date startDate, Date endDate) {
        return getRepository().getExpenseByAccount(accountId, startDate, endDate);
    }

    public OrderStatistic getSumExpenseByAccount(int accountId, Date startDate, Date endDate) {
        List<OrderStatistic> statistics = getRepository().getExpenseByAccount(accountId, startDate, endDate);
        BigDecimal sumAmount = new BigDecimal(0);
        for (OrderStatistic st : statistics){
            sumAmount = sumAmount.add(st.getAmount());
        }
        return new OrderStatistic(accountService.getById(accountId), null , null, sumAmount);
    }

    public OrderStatistic getSumIncomeByAccount(int accountId, Date startDate, Date endDate) {
        List<OrderStatistic> statistics = getRepository().getIncomeByAccount(accountId, startDate, endDate);
        BigDecimal sumAmount = new BigDecimal(0);
        for (OrderStatistic st : statistics){
            sumAmount = sumAmount.add(st.getAmount());
        }
        return new OrderStatistic(accountService.getById(accountId), null , null, sumAmount);
    }

    public List<OrderStatistic> getExpenseByCategory(Category category, Date startDate, Date endDate) {
        return getRepository().getExpenseByCategory(category, startDate, endDate);
    }

    public List<Order> getOrdersByPeriod(Periods periods, String startDate, String endDate){

        dateProcessor.createPeriod(periods, startDate, endDate);
        return getRepository().findByDateBetween(dateProcessor.getStartDate().toDate(), dateProcessor.getEndDate().toDate());
    }

}
