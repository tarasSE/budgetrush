package com.provectus.budgetrush;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.services.AccountService;
import com.provectus.budgetrush.services.AccountServiceBean;

public class AccountTest {

    private final AccountService SERVICE = new AccountServiceBean();
    private final String CATEGORY_NAME = "goods";

    @Test
    @Transactional
    public void saveAccountTest() throws Exception {
        User userDTO = new User();
        userDTO.setId(1);
        userDTO.setName("userDTO");
        userDTO.setPassword("pass");

        Currency currencyDTO = new Currency();
        currencyDTO.setId(1);
        currencyDTO.setName("United States Dollar");
        currencyDTO.setShortname("USD");
        currencyDTO.setCode("666");
        currencyDTO.setSymbol('x');

        Account accountDTO = new Account();
        accountDTO.setName(CATEGORY_NAME);
        accountDTO.setId(1);
        accountDTO.setUser(userDTO);
        accountDTO.setCurrency(currencyDTO);
        SERVICE.addAccount(accountDTO);

        assertNotNull(accountDTO.getId());
    }

    @Test
    @Transactional
    public void getByNameAccountTest() throws Exception {
        Account accountDTO = SERVICE.getByName(CATEGORY_NAME);

        assertEquals(accountDTO.getName(), CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void getByIdAccountTest() throws Exception {
        Account accountDTO = SERVICE.getById(1);

        assertEquals(accountDTO.getName(), CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void deleteAccountTest() throws Exception {
        int size = SERVICE.getAll().size();
        SERVICE.delete(1);

        assertEquals(size - 1, SERVICE.getAll().size());
    }
}
