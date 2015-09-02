package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.*;
import com.provectus.budgetrush.service.OrderService;
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
@ContextConfiguration(classes = { HibernateConfig.class, OrderService.class })
@WebAppConfiguration
public class OrdersTest {

        private final Random random = new Random();
        @Resource
        private EntityManagerFactory emf;
        protected EntityManager em;

        @Autowired
        private OrderService service;

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

            Currency currency = new Currency();
            currency.setName("test_name");
            currency.setShortname("usd");
            currency.setCode(1111);
            currency.setSymbol('s');

            Account account = new Account();
            account.setCurrency(currency);
            account.setUser(user);
            account.setName("test_name");

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

            return service.createAndUpdate(order);
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
    }


