package com.provectus.budgetrush.jacksontest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
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
import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.datatest.InMemoryConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, ObjectMapper.class, Currency.class })
@WebAppConfiguration
public class CurrencyMappingTest {

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
        File file = new File("currency.json");
        Currency currency = new Currency();
        Currency currency1;

        currency.setName("test_name");
        currency.setShortName("USD");
        currency.setCode(1234);
        currency.setSymbol('x');

        log.info("Writing JSON to file");
        mapper.writeValue(file, currency);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        currency1 = mapper.readValue(file, Currency.class);
        log.info(currency1.toString());

        file.delete();

        assertNotNull(file.toString(), currency1);
    }

}
