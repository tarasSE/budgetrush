package com.provectus.budgetrush.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/accounts", headers = "Accept=application/json")
@Controller
public class AccountController {

    @Autowired
    private AccountService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Account> listAll() {
        log.info("Get all accounts");
        List<Account> accounts = service.getAll();
        log.info("Send all accounts " + accounts.size());
        return accounts;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Account getById(@PathVariable Integer id) {
        log.info("Get account by id " + id);
        Account account = service.getById(id);
        if (account != null) {
            log.info("Send account " + account.getName());
        }

        return account;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @RequestMapping(value = "/v1/accounts", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Account newUser(@RequestBody Account account) {
        log.info("Save account " + account.getName());
        account.setId(0);
        return service.createOrUpdate(account);

    }

    @RequestMapping(value = "/v1/accounts/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public Account saveUser(@RequestBody Account account, @PathVariable Integer id) {
        log.info("Save account " + account.getName());
        account.setId(id);
        return service.createOrUpdate(account);
    }
}
