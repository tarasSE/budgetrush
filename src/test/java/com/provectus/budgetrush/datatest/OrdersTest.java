package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;
import com.provectus.budgetrush.dateproc.DateProcessorBean;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.*;

import static com.provectus.budgetrush.data.Periods.LAST_YEAR;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.*;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, OrderService.class, AccountService.class,
        CategoryService.class, ContractorService.class, DateProcessorBean.class})
@WebAppConfiguration
public class OrdersTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private OrderService service;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private DateProcessorBean dateProcessor;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Order saveTestOrder() {
        log.info("Start save test order");

        Order order = new Order();
        order.setAmount(valueOf(random.nextDouble()));
        Calendar startDate = new GregorianCalendar(2015, 9, 4, 14, 0);
        order.setDate(new Date(startDate.getTimeInMillis()));
        order.setAccount(accountService.getById(1));
        order.setCategory(categoryService.getById(1));
        order.setContractor(contractorService.getById(1));

        return service.create(order);
    }

    @Test
    @Transactional
    public void saveOrderTest() throws Exception {

        Order order = saveTestOrder();
        assertNotNull(order.getId());

    }

    @Test
    @Transactional
    public void getAllOrdersTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestOrder();
        List<Order> orders = service.getAll();
        for (Order order : orders) {
            log.info("Order " + order.getAmount() + " id " + order.getId());
        }

        assertNotEquals(size, orders.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Order order = saveTestOrder();
        Order order1 = service.getById(order.getId());

        assertEquals(order.getId(), order1.getId());
        log.info("id1 " + order.getId() + " id2 " + order1.getId());
    }

    @Test
    @Transactional
    public void deleteOrderTest() throws Exception {
        Order order = saveTestOrder();
        service.delete(order.getId());

        log.info("id  " + order.getId());
    }

    @Test
    public void getAmountMovement() {
        Order order = saveTestOrder();
        assertNotNull(order);
        Account account = order.getAccount();

        Calendar startDate = new GregorianCalendar(2015, 9, 4, 0, 0);
        Calendar endDate = new GregorianCalendar(2015, 9, 4, 23, 59);
        List<OrderStatistic> ammounts = service.getTurnoverByAccount(account.getId(),
                new Date(startDate.getTimeInMillis()), new Date(endDate.getTimeInMillis()));

        assertNotNull(ammounts);
        assertEquals(ammounts.isEmpty(), false);
        for (OrderStatistic amountMovement : ammounts) {
            log.info("Amount movement :" + amountMovement.toString());
        }

    }

    @Test
    public void getIncomeByAccount() {
        Order order = saveTestOrder();
        order.setAmount(BigDecimal.valueOf(-656.00));
        assertNotNull(order);
        Account account = order.getAccount();

        Date startDate = new Date(0);
        Date endDate = new Date();
        List<OrderStatistic> incomes = service.getIncomeByAccount(account.getId(), startDate, endDate);

        assertNotNull(incomes);
        assertEquals(incomes.isEmpty(), false);
        for (OrderStatistic income : incomes) {
            log.info("Income :" + income.toString());
        }

    }

    @Test
    public void getExpenseByAccount() {
        Order order = saveTestOrder();
        order.setAmount(BigDecimal.valueOf(-656.00));
        assertNotNull(order);

        Date startDate = new Date(0);
        Date endDate = new Date();
        List<OrderStatistic> expenses = service.getExpenseByAccount(order.getAccount().getId(),
                startDate, endDate);

        assertNotNull(expenses);
        // assertEquals(expenses.isEmpty(), false);
        for (OrderStatistic expense : expenses) {
            log.info("Expense :" + expense.toString());
        }

    }

    @Test
    public void getOrdersByPeriodTest(){

       List<Order> orders =  service.getOrdersByPeriod(LAST_YEAR, null, null);

        for (Order order: orders) {
            log.info(order.toString());
        }

    }
}
