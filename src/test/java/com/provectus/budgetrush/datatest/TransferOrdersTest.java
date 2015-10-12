package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.*;
import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.service.*;
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

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.*;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryConfig.class, TransferOrderService.class, OrderService.class,
        AccountService.class, CurrencyService.class, ContractorService.class,
        UserService.class, CategoryService.class})
@WebAppConfiguration
public class TransferOrdersTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private TransferOrderService transferService;
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ContractorService contractorService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }


    private TransferOrder saveTestTransferOrder() {
        log.info("Start save test transferOrder");

        User user = new User();
        user.setId(1);
        user.setName("test_name");
        user.setPassword("pass");


        Currency currency = new Currency();
        currency.setId(1);
        currency.setName("test_name");
        currency.setShortName("usd");
        currency.setCode(1111);
        currency.setSymbol('s');


        Account account = new Account();
        account.setId(1);
        account.setCurrency(currency);
        account.setUser(user);
        account.setName("test_name");


        Contractor contractor = new Contractor();
        contractor.setId(1);
        contractor.setName("test_name");
        contractor.setDescription("test_description");


        Category category = new Category();
        category.setId(1);
        category.setName("test_category");
        category.setParent(null);


        Order order = new Order();
        order.setId(1);
        order.setAmount(valueOf(-999));
        Calendar startDate = new GregorianCalendar(2015, 9, 4, 14, 0);
        order.setDate(new Date(startDate.getTimeInMillis()));
        order.setAccount(account);
        order.setCategory(category);
        order.setContractor(contractor);


        Account account1 = account;
        account1.setId(2);


        Order order1 = order;
        order1.setAmount(valueOf(999));
        order1.setId(2);


        TransferOrder transferOrder = new TransferOrder();

        transferOrder.setId(3);
        transferOrder.setAmount(valueOf(999));
        transferOrder.setDate(new Date());
        transferOrder.setAccount(account);
        transferOrder.setTransferAccount(account1);
        transferOrder.setCategory(category);
        transferOrder.setContractor(contractor);
        transferOrder.setType(OrderType.TRANSFER_ORDER);
        transferOrder.setExpense(order);
        transferOrder.setIncome(order1);

        return transferOrder;
    }

    @Test
    @Transactional
    public void saveTransferOrderTest() throws Exception {

        TransferOrder transferOrder = saveTestTransferOrder();
        assertNotNull(transferOrder.getId());

    }

    @Test
    @Transactional
    public void getAllTransferOrdersTest() throws Exception {
        log.info("Start get all test");
        int size = transferService.getAll().size();
        saveTestTransferOrder();
        List<TransferOrder> orders = transferService.getAll();
        for (TransferOrder transferOrder : orders) {
            log.info("TransferOrder " + transferOrder.getAmount() + " id " + transferOrder.getId());
        }

        assertNotEquals(size, orders.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        TransferOrder transferOrder = saveTestTransferOrder();
        TransferOrder order1 = transferService.getById(transferOrder.getId());

        assertEquals(transferOrder.getId(), order1.getId());
        log.info("id1 " + transferOrder.getId() + " id2 " + order1.getId());
    }

    @Test
    @Transactional
    public void deleteTransferOrderTest() throws Exception {
        TransferOrder transferOrder = saveTestTransferOrder();
        transferService.delete(transferOrder.getId());

        log.info("id  " + transferOrder.getId());
    }

    @Test
    @Transactional
    public void transferTest() throws Exception {
        TransferOrder tro = saveTestTransferOrder();
        TransferOrder transferOrder = new TransferOrder();
        transferOrder.setAmount(BigDecimal.ONE);
        transferOrder.setAccount(tro.getAccount());
        transferOrder.setTransferAccount(tro.getTransferAccount());

        transferService.transfer(transferOrder);

        log.info("id " + transferOrder.getId());
    }
}
