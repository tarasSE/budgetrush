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
    @Autowired
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

    private Order saveTestOrder() {
        log.info("Start save test order");

        User user = new User();
        user.setName("test_name");
        user.setPassword("pass");
        user=userService.create(user);

        Currency currency = new Currency();
        currency.setName("test_name");
        currency.setShortName("usd");
        currency.setCode(1111);
        currency.setSymbol('s');
        currency=currencyService.create(currency);

        Account account = new Account();
        account.setCurrency(currency);
        account.setUser(user);
        account.setName("test_name");
        account.setBalance(valueOf(578568));
        account=accountService.create(account);

        Contractor contractor = new Contractor();
        contractor.setName("test_name");
        contractor.setDescription("test_description");
        contractor.setUser(user);
        contractor=contractorService.create(contractor);

        Category category = new Category();
        category.setName("test_category");
        category.setParent(null);
        category.setUser(user);
        category=categoryService.create(category);

        Order order = new Order();
        order.setAmount(valueOf(random.nextDouble()));
        Calendar startDate = new GregorianCalendar(2015, 9, 4, 14, 0);
        order.setDate(new Date(startDate.getTimeInMillis()));
        order.setAccount(account);
        order.setCategory(category);
        order.setContractor(contractor);

        return orderService.create(order);
    }

    private TransferOrder saveTestTransferOrder() {
        log.info("Start save test transferOrder");

        Order order = saveTestOrder();

        Order order1 = saveTestOrder();

        TransferOrder transferOrder = new TransferOrder();
        transferOrder.setAmount(valueOf(999));
        transferOrder.setDate(new Date());
        transferOrder.setAccount(order.getAccount());
        transferOrder.setTransferAccount(order1.getAccount());
        transferOrder.setCategory(null);
        transferOrder.setContractor(null);
        transferOrder.setType(OrderType.TRANSFER_ORDER);
        transferOrder.setExpense(order);
         transferOrder.setIncome(order1);

        return  transferService.create(transferOrder);
    }

    @Test
    public void saveTransferOrderTest() throws Exception {

        TransferOrder transferOrder = saveTestTransferOrder();
        assertNotNull(transferOrder.getId());

    }

    @Test
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
    public void getByIdTest() throws Exception {
        TransferOrder transferOrder = saveTestTransferOrder();
        TransferOrder order1 = transferService.getById(transferOrder.getId());

        assertEquals(transferOrder.getId(), order1.getId());
        log.info("id1 " + transferOrder.getId() + " id2 " + order1.getId());
    }

    @Test
    public void deleteTransferOrderTest() throws Exception {
        TransferOrder transferOrder = saveTestTransferOrder();
        transferService.delete(transferOrder.getId());

        log.info("id  " + transferOrder.getId());
    }

    @Test
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
