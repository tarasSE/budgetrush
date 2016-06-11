package com.provectus.budgetrush.controllers;

import java.util.List;

import javax.inject.Inject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.currency.Currency;
import com.provectus.budgetrush.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/currencies", headers = "Accept=application/json")
@RestController
public class CurrencyController {

    @Inject
    private CurrencyService currencyService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Currency> listAll() {
        log.info("Send all Currencies");
        return currencyService.getAll();
    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Currency getById(@PathVariable Integer id) {
        log.info("Send currency by id " + id);
        Currency currency = currencyService.getById(id);
        return currency;

    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        currencyService.delete(id);
    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Currency newCurrency(@RequestBody Currency currency) {
        log.info("Save currency " + currency.getName());
        return currencyService.create(currency);

    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Currency saveCurency(@RequestBody Currency currency, @PathVariable Integer id) {
        log.info("Save currency " + currency.getName());
        return currencyService.update(currency, id);
    }
}
