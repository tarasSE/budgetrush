package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Currency;

/**
 * Created by taras on 28.08.15.
 */
@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    public Currency findByName(String name);
}