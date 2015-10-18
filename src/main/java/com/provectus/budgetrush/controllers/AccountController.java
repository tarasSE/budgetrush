package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.service.AccountService;

@Slf4j
@RequestMapping(value = "/v1/accounts", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @PostFilter("inGroupOrAdmin(filterObject.group, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Account> listAll() {
        log.info("Get all accounts");
        List<Account> accounts = service.getAll();
        log.info("Send all accounts " + accounts.size());
        return accounts;
    }

    @PostAuthorize("inGroupOrAdmin(returnObject.group, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Account getById(@PathVariable Integer id) {
        log.info("Get account by id " + id);
        Account account = service.getById(id);
        log.info("Send account " + account.getName());

        return account;

    }

    @PreAuthorize("inGroupOrAdmin(@accountService.getById(#id).group, 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete account by id " + id);
        service.delete(id);
    }

    @PreAuthorize("inGroupOrAdmin(#user.group, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Account newUser(@RequestBody Account account) {
        log.info("Save account " + account.getName());
        return service.create(account);

    }

    @PreAuthorize("inGroupOrAdmin(#user.group, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Account saveUser(@RequestBody Account account, @PathVariable Integer id) {
        log.info("Save account " + account.getName());
        return service.update(account, id);
    }
}
