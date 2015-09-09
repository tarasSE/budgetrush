package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.repository.CurrencyRepository;

@Service
@Transactional
public class CurrencyService extends GenericService<Currency, CurrencyRepository> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    protected CurrencyRepository getRepository() {
        return currencyRepository;
    }
}
