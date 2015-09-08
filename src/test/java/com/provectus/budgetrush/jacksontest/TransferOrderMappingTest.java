package com.provectus.budgetrush.jacksontest;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.datatest.InMemoryConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, ObjectMapper.class, TransferOrder.class, User.class, Account.class, Category.class, Contractor.class })
@WebAppConfiguration
public class TransferOrderMappingTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        log.info("Init object mapper");
        mapper = new ObjectMapper();
    }

    @Test
    @Transactional
    public void jsonMappingTest() throws Exception {
        Scanner scanner;
        File file = new File("order.json");
        TransferOrder order1;

        User user = new User();
        user.setName("test_name");
        user.setPassword("pass");

        Currency currency = new Currency();
        currency.setName("test_name");
        currency.setShortName("usd");
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

        TransferOrder order = new TransferOrder();
        order.setAmount(valueOf(1234124));
        order.setDate(new Date());
        order.setAccount(account);
        order.setCategory(category);
        order.setContractor(contractor);

        log.info("Writing JSON to file");
        mapper.writeValue(file, order);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        order1 = mapper.readValue(file, TransferOrder.class);
        log.info(order1.toString());

        file.delete();

        assertNotNull(file.toString(), order1);
    }

}
