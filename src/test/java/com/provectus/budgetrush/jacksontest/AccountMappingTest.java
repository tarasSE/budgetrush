package com.provectus.budgetrush.jacksontest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Scanner;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.datatest.InMemoryConfig;
import com.provectus.budgetrush.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectMapper.class, InMemoryConfig.class, AccountService.class })
@WebAppConfiguration
public class AccountMappingTest {

    private ObjectMapper mapper;
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        log.info("Init object mapper");
        mapper = new ObjectMapper();
        em = emf.createEntityManager();
    }

    @Test
    public void jsonMappingTest() throws Exception {
        Scanner scanner;
        File file = new File("account.json");

        log.info("Writing JSON to file");
        mapper.writeValue(file, accountService.getById(1));
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();
        ;

        log.info("Creating POJO from JSON");
        Account account1 = mapper.readValue(file, Account.class);
        log.info(account1.toString());

        file.delete();

        assertNotNull(file.toString(), account1);
    }

}
