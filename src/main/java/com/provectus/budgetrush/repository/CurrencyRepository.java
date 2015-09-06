package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Currency;


@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    public Currency findByName(String name);
}