package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;

/**
 * Created by taras on 28.08.15.
 */
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByName(String name);
}