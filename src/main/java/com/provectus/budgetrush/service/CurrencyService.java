package com.provectus.budgetrush.service;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.currency.Currency;
import com.provectus.budgetrush.repository.CurrencyRepository;

@Service
@Repository
@Transactional(readOnly = true)
public class CurrencyService extends GenericService<Currency, CurrencyRepository> {

    @Inject
    private CurrencyRepository currencyRepository;

    @Override
    protected CurrencyRepository getRepository() {
        return currencyRepository;
    }
}
