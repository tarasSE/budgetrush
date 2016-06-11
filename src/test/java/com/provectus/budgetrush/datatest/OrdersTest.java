package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.FilterEnum;
import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.order.Order;
import com.provectus.budgetrush.dateprocessor.DateProcessorBean;
import com.provectus.budgetrush.dateprocessor.Period;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.OrderService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.provectus.budgetrush.data.PeriodsEnum.LAST_YEAR;
import static com.provectus.budgetrush.data.PeriodsEnum.TODAY;
import static java.math.BigDecimal.valueOf;

@ContextConfiguration(classes = {
        OrderService.class,
        AccountService.class,
        CategoryService.class,
        ContractorService.class,
        DateProcessorBean.class
})

public class OrdersTest extends TestGenericService<Order, OrderService> {
    @Inject
    private OrderService orderService;

    @Inject
    private AccountService accountService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ContractorService contractorService;

    @Inject
    private DateProcessorBean dateProcessor;

    @Test
    public void getOrdersByPeriodTest() {
        Period period = dateProcessor.createPeriod(TODAY, null, null);
        List<Order> orders = orderService.getOrdersByPeriod(
                period.getStartDate(),
                period.getEndDate());
    }

    @Test
    public void getOrdersByPeriodAndAccountAndFilter() {
        Account account = accountService.getById(1);
        Period period = dateProcessor.createPeriod(LAST_YEAR, null, null);

        List<Order> orders = orderService.getOrdersByPeriodAndAccountAndFilter(
                account,
                period.getStartDate(),
                period.getEndDate(),
                FilterEnum.INCOME);

        orders = orderService.getOrdersByPeriodAndAccountAndFilter(
                account,
                period.getStartDate(),
                period.getEndDate(),
                FilterEnum.EXPENSE);

        orders = orderService.getOrdersByPeriodAndAccountAndFilter(
                account,
                period.getStartDate(),
                period.getEndDate(),
                null);
    }

    @Override
    protected Order getEntity() {
        Calendar startDate = new GregorianCalendar(2015, 9, 4, 14, 0);

        Order order = new Order();
        order.setAmount(valueOf(random.nextDouble()));
        order.setDate(new Date(startDate.getTimeInMillis()));
        order.setAccount(accountService.getById(1));
        order.setCategory(categoryService.getById(1));
        order.setContractor(contractorService.getById(1));

        return order;
    }

    @Override
    protected OrderService getService() {
        return orderService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
