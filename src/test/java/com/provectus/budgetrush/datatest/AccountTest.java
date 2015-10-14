package com.provectus.budgetrush.datatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;

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

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CurrencyService;
import com.provectus.budgetrush.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, AccountService.class, GroupService.class,
        CurrencyService.class })
@WebAppConfiguration
public class AccountTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private AccountService service;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private GroupService groupService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Account saveTestAccount() {
        log.info("Start save test Account");
        Account account = new Account();

        account.setName(Integer.toString(random.nextInt()));

        account.setGroup(groupService.getById(1));

        account.setCurrency(currencyService.getById(1));

        return service.create(account);
    }

    @Test
    @Transactional
    public void saveAccountTest() throws Exception {
        Account account = saveTestAccount();
        assertNotNull(account.getId());
    }

    @Test
    @Transactional
    public void getAllCategoriesTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestAccount();
        List<Account> accounts = service.getAll();
        for (Account account : accounts) {
            log.info("Account " + account.getName() + " id " + account.getId());
        }

        assertNotEquals(size, accounts.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Account account = saveTestAccount();
        Account account1 = service.getById(account.getId());

        assertEquals(account.getId(), account1.getId());
        log.info("id1 " + account.getId() + " id2 " + account1.getId());
    }

    @Test
    @Transactional
    public void deleteAccountTest() throws Exception {
        Account account = saveTestAccount();
        service.delete(account.getId());

        log.info("id  " + account.getId());
    }
}
