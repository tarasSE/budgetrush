package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taras on 28.08.15.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    public Currency findByName(String name);
}
