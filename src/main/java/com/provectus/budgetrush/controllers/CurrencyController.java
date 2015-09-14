package com.provectus.budgetrush.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @RequestMapping(value = "/currencies", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Currency> listAll() {
        log.info("Send all Currencies");
        return service.getAll();
    }

    @RequestMapping(value = "/currencies/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Currency getById(@PathVariable Integer id) {
        log.info("Send currency by id " + id);
        Currency currency = service.getById(id);
        return currency;

    }

    @RequestMapping(value = "/currencies/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @RequestMapping(value = "/currencies", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Currency newUser(@RequestBody Currency currency) {
        log.info("Save currency " + currency.getName());
        currency.setId(0);
        return service.createAndUpdate(currency);
    }

    @RequestMapping(value = "/currencies/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public Currency saveUser(@RequestBody Currency currency, @PathVariable Integer id) {
        log.info("Save currency " + currency.getName());
        currency.setId(id);
        return service.createAndUpdate(currency);
    }
}
