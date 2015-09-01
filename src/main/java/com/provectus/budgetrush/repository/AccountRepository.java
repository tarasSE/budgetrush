package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taras on 28.08.15.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByName(String name);
}
