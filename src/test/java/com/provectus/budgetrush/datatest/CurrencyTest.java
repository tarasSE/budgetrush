package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.currency.Currency;
import com.provectus.budgetrush.service.CurrencyService;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

@ContextConfiguration(classes = {
        CurrencyService.class
})

public class CurrencyTest extends TestGenericService<Currency, CurrencyService> {
    @Inject
    private CurrencyService currencyService;

    protected CurrencyService getService() {
        return currencyService;
    }

    @Override
    protected Currency getEntity() {
        Currency currency = new Currency();

        currency.setName("USD");
        currency.setCode(840);
        currency.setShortName("USD");
        currency.setSymbol('$');

        return currency;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
