package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends GenericService <Account, AccountRepository > {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    protected AccountRepository getRepository() {
        return accountRepository;
    }
}
