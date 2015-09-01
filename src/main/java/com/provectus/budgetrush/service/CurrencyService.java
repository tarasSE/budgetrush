package com.provectus.budgetrush.service;

import java.util.List;

import com.provectus.budgetrush.data.Currency;

/**
 * Created by taras on 28.08.15.
 */
public interface CurrencyService {

    Currency addCurrency(Currency currencyDTO);

    void delete(int id);

    Currency getById(int id);

    Currency getByName(String name);

    Currency editCurrency(Currency currencyDTO);

    List<Currency> getAll();
}