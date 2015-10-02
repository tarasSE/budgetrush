package com.provectus.budgetrush.jacksontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.datatest.InMemoryConfig;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CurrencyService;
import com.provectus.budgetrush.service.UserService;
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

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, ObjectMapper.class, Account.class, AccountService.class, UserService.class, CurrencyService.class })
@WebAppConfiguration
public class AccountMappingTest {

    private ObjectMapper mapper;
    private final Random random = new Random();

    @Autowired
    private AccountService service;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;

    @Before
    public void setUp() throws Exception {
        log.info("Init object mapper");
        mapper = new ObjectMapper();
    }

    @Test
    @Transactional
    public void jsonMappingTest() throws Exception {
        Scanner scanner;
        File file = new File("account.json");
        Account account = new Account();
        Account account1;

        account.setName(Integer.toString(random.nextInt()));

        User user = new User();
        user.setName(Integer.toString(random.nextInt()));
        user.setPassword(Integer.toString(random.nextInt()));
        account.setUser(userService.create(user));

        Currency currency = new Currency();

        currency.setName("USD");
        currency.setCode(840);
        currency.setShortName("USD");
        currency.setSymbol('$');
        account.setCurrency(currencyService.create(currency));

        log.info("Writing JSON to file");
        mapper.writeValue(file, account);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();
        ;

        log.info("Cresting POJO from JSON");
        account1 = mapper.readValue(file, Account.class);
        log.info(account1.toString());

        file.delete();

        assertNotNull(file.toString(), account1);
    }

}
