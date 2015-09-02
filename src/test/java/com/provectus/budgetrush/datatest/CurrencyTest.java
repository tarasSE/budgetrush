package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.Currency;
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
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, CurrencyServiceBean.class })
@WebAppConfiguration
public class CurrencyTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private CurrencyService service;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Currency saveTestCurrency() {
        log.info("Start save test category");
        Currency currency = new Currency();

        currency.setName("USD");
        currency.setCode(840);
        currency.setShortname("USD");
        currency.setSymbol('$');
        return service.addCurrency(currency);
    }

    @Test
    @Transactional
    public void saveCurrencyTest() throws Exception {

        Currency currency = saveTestCurrency();
        assertNotNull(currency.getId());

    }

    @Test
    @Transactional
    public void getAllCurrencyTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestCurrency();
        List<Currency> currencies = service.getAll();
        for (Currency currency : currencies) {
            log.info("Currency " + currency.getName() + " id " + currency.getId());
        }

        assertNotEquals(size, currencies.size());
    }

    @Test
    @Transactional
    public void getByNameTest() throws Exception {
        Currency currency = saveTestCurrency();
        Currency currency1 = service.getByName(currency.getName());

        assertEquals(currency.getName(), currency1.getName());
        assertEquals(currency.getId(), currency1.getId());
        log.info("id1 " + currency.getId() + " id2 " + currency.getId());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Currency currency = saveTestCurrency();
        Currency currency1 = service.getById(currency.getId());

        assertEquals(currency.getId(), currency1.getId());
        log.info("id1 " + currency.getId() + " id2 " + currency1.getId());
    }

    @Test
    @Transactional
    public void deleteCurrencyTest() throws Exception {
        Currency currency = saveTestCurrency();
        service.delete(currency.getId());

        log.info("id  " + currency.getId());
    }
}
