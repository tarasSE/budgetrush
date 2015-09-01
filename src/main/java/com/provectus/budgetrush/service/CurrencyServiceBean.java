package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provectus.budgetrush.data.Currency;
import com.provectus.budgetrush.repository.CurrencyRepository;

/**
 * Created by taras on 28.08.15.
 */
@Service
public class CurrencyServiceBean implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency addCurrency(Currency currencyDTO) {
        return currencyRepository.saveAndFlush(currencyDTO);
    }

    @Override
    public void delete(int id) {
        currencyRepository.delete(id);
    }

    @Override
    public Currency getById(int id) {
        return currencyRepository.findOne(id);
    }

    @Override
    public Currency getByName(String name) {
        return currencyRepository.findByName(name);
    }

    @Override
    public Currency editCurrency(Currency currencyDTO) {
        return currencyRepository.saveAndFlush(currencyDTO);
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }
}
