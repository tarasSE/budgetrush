package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService extends GenericService <Currency, CurrencyRepository> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    protected CurrencyRepository getRepository() {
        return currencyRepository;
    }
}
