package com.provectus.budgetrush.datatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;
import java.util.Set;

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

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CurrencyService;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, AccountService.class, UserService.class,
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
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Account saveTestAccount() {
        log.info("Start save test Account");
        Account account = new Account();

        account.setName(Integer.toString(random.nextInt()));

        User user = userService.getById(1);

        Set<Group> groups = user.getGroups();

        Preconditions.checkNotNull(!groups.isEmpty(), "Can`t find user!");
        account.setGroup(groups.iterator().next());

        account.setCurrency(currencyService.getById(1));

        return service.create(account);
    }

    @Test
    public void saveAccountTest() throws Exception {
        Account account = saveTestAccount();
        assertNotNull(account.getId());
    }

    @Test
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
    public void getByIdTest() throws Exception {
        Account account = saveTestAccount();
        Account account1 = service.getById(account.getId());

        assertEquals(account.getId(), account1.getId());
        log.info("id1 " + account.getId() + " id2 " + account1.getId());
    }

    @Test
    public void deleteAccountTest() throws Exception {
        Account account = saveTestAccount();
        service.delete(account.getId());

        log.info("id  " + account.getId());
    }
}
