package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.*;
import com.provectus.budgetrush.utils.HibernateConfig;
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
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, TransferOrderServiceBean.class })
@WebAppConfiguration
public class TransferOrdersTest {

        private final Random random = new Random();
        @Resource
        private EntityManagerFactory emf;
        protected EntityManager em;

        @Autowired
        private TransferOrderService service;

        @Before
        public void setUp() throws Exception {
            log.info("Init entity manager");
            em = emf.createEntityManager();
        }

        private TransferOrder saveTestTransferOrder() {
            log.info("Start save test transferOrder");


            User user = new User();
            user.setName("test_name");
            user.setPassword("pass");

            Currency currency = new Currency();
            currency.setName("test_name");
            currency.setShortname("usd");
            currency.setCode(1111);
            currency.setSymbol('s');

            Account account = new Account();
            account.setCurrency(currency);
            account.setUser(user);
            account.setName("test_name");

            Account account1 = new Account();
            account1.setCurrency(currency);
            account1.setUser(user);
            account1.setName("test_name");

            Contractor contractor = new Contractor();
            contractor.setName("test_name");
            contractor.setDescription("test_description");

            Category category = new Category();
            category.setName("test_category");
            category.setParent(null);

            Order order = new Order();
            order.setAmount(random.nextInt());
            order.setDate(new Date());
            order.setAccount(account);
            order.setCategory(category);
            order.setContractor(contractor);

            Order order1 = new Order();
            order1.setAmount(random.nextInt());
            order1.setDate(new Date());
            order1.setAccount(account);
            order1.setCategory(category);
            order1.setContractor(contractor);

            TransferOrder transferOrder = new TransferOrder();
            transferOrder.setAmount(random.nextInt());
            transferOrder.setDate(new Date());
            transferOrder.setAccount(account);
            transferOrder.setCategory(category);
            transferOrder.setContractor(contractor);
            transferOrder.setIncomeOrder(order);
            transferOrder.setExpenseOrder(order1);

            return service.addTransferOrder(transferOrder);
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
            int size = service.getAll().size();
            saveTestTransferOrder();
            List<TransferOrder> orders = service.getAll();
            for (TransferOrder transferOrder : orders) {
                log.info("TransferOrder " + transferOrder.getAmount() + " id " + transferOrder.getId());
            }

            assertNotEquals(size, orders.size());
        }

        @Test
        @Transactional
        public void getByIdTest() throws Exception {
            TransferOrder transferOrder = saveTestTransferOrder();
            TransferOrder order1 = service.getByID(transferOrder.getId());

            assertEquals(transferOrder.getId(), order1.getId());
            log.info("id1 " + transferOrder.getId() + " id2 " + order1.getId());
        }

        @Test
        @Transactional
        public void editTransferOrderTest() throws Exception {
            TransferOrder transferOrder = saveTestTransferOrder();
            TransferOrder order1 = service.getByID(transferOrder.getId());

            order1.setAmount(1234567.89);

            service.editTransferOrder(order1);
            assertEquals(transferOrder.getId(), order1.getId());
            log.info("id1 " + transferOrder.getId() + " id2 " + order1.getId());
        }


        @Test
        @Transactional
        public void deleteTransferOrderTest() throws Exception {
            TransferOrder transferOrder = saveTestTransferOrder();
            service.delete(transferOrder.getId());

            log.info("id  " + transferOrder.getId());
        }
    }


