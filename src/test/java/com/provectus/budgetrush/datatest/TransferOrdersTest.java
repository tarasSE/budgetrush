package com.provectus.budgetrush.datatest;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.order.OrderType;
import com.provectus.budgetrush.data.order.TransferOrder;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.OrderService;
import com.provectus.budgetrush.service.TransferOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, TransferOrderService.class, OrderService.class,
        AccountService.class, ContractorService.class, CategoryService.class })
@WebAppConfiguration
public class TransferOrdersTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private TransferOrderService transferService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private TransferOrder saveTestTransferOrder() {
        log.info("Start save test transferOrder");

        TransferOrder transferOrder = new TransferOrder();

        transferOrder.setId(3);
        transferOrder.setAmount(valueOf(999));
        transferOrder.setDate(new Date());
        transferOrder.setAccount(accountService.getById(1));
        transferOrder.setTransferAccount(accountService.getById(2));
        transferOrder.setCategory(categoryService.getById(1));
        transferOrder.setContractor(contractorService.getById(1));
        transferOrder.setType(OrderType.TRANSFER_ORDER);

        return transferService.create(transferOrder);
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

}