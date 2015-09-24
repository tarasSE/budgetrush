package com.provectus.budgetrush.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/currencies", headers = "Accept=application/json")
@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Currency> listAll() {
        log.info("Send all Currencies");
        return service.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Currency getById(@PathVariable Integer id) {
        log.info("Send currency by id " + id);
        Currency currency = service.getById(id);
        return currency;

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Currency newUser(@RequestBody Currency currency) {
        log.info("Save currency " + currency.getName());
        currency.setId(0);
        return service.createOrUpdate(currency);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Currency saveUser(@RequestBody Currency currency, @PathVariable Integer id) {
        log.info("Save currency " + currency.getName());
        currency.setId(id);
        return service.createOrUpdate(currency);
    }
}
