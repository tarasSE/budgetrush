package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.repository.AccountRepository;

@Service
@Transactional
public class AccountService extends GenericService<Account, AccountRepository> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected AccountRepository getRepository() {
        return accountRepository;
    }
}
