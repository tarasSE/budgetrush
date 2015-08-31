package com.provectus.budgetrush.services;

import com.provectus.budgetrush.data.Account;

import java.util.List;

/**
 * Created by taras on 28.08.15.
 */
public interface AccountService {

    Account addAccount(Account accountDTO);

    void delete(int id);

    Account getById(int id);

    Account getByName(String name);

    Account editAccount(Account accountDTO);

    List<Account> getAll();
}
