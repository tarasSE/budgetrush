package com.provectus.budgetrush.service;

import java.util.List;

import com.provectus.budgetrush.data.Account;

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
