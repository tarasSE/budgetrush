package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.currency.Currency;

@Repository
@Transactional(readOnly = true)
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    public Currency findByName(String name);
}