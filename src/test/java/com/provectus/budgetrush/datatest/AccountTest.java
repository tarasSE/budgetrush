package com.provectus.budgetrush.datatest;


import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.account.AccountStatistic;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CurrencyService;
import com.provectus.budgetrush.service.GroupService;
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
    
    @Test
    public void getAmountMovement() {
        Account account = saveTestAccount();
        assertNotNull(account);
        Date startDate = new Date(0);
        Date endDate = new Date();
        
        List<AccountStatistic> ammounts = service.getTurnoverByAccount(account.getId(),
        		startDate, endDate);

        assertNotNull(ammounts);
        for (AccountStatistic amountMovement : ammounts) {
            log.info("Amount movement :" + amountMovement.toString());
        }

    }

    @Test
    public void getIncomeByAccount() {
    	Account account = saveTestAccount();
        assertNotNull(account);
        Date startDate = new Date(0);
        Date endDate = new Date();
        List<AccountStatistic> incomes = service.getIncomeByAccount(account.getId(), startDate, endDate);

        assertNotNull(incomes);
        for (AccountStatistic income : incomes) {
            log.info("Income :" + income.toString());
        }

    }

    @Test
    public void getExpenseByAccount() {
    	Account account = saveTestAccount();
        assertNotNull(account);
        Date startDate = new Date(0);
        Date endDate = new Date();
        
        List<AccountStatistic> expenses = service.getExpenseByAccount(account.getId(),
                startDate, endDate);

        assertNotNull(expenses);
        for (AccountStatistic expense : expenses) {
            log.info("Expense :" + expense.toString());
        }

    }
}
