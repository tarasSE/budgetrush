package com.provectus.budgetrush.services;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by taras on 28.08.15.
 */
@Service
public class AccountServiceBean implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account addAccount(Account accountDTO) {
        return accountRepository.saveAndFlush(accountDTO);
    }

    @Override
    public void delete(int id) {
        accountRepository.delete(id);
    }

    @Override
    public Account getById(int id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account getByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    public Account editAccount(Account accountDTO) {
        return accountRepository.saveAndFlush(accountDTO);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
