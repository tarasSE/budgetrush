package com.provectus.budgetrush.service;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional
    public Account create(Account account) {
        if (account.getBalance() == null) {
            account.setBalance(new BigDecimal(0));
        }
        return super.create(account);
    }

    @Transactional
    public Account incressBalance(@NotNull Account account, BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance == null) {
            balance = new BigDecimal(0);
        }

        account.setBalance(balance.add(amount));
        return update(account, account.getId());
    }

    @Transactional
    public Account decreaseBalance(Account account, BigDecimal amount) {
        BigDecimal balance = account.getBalance();
        if (balance == null) {
            balance = new BigDecimal(0);
        }
        account.setBalance(balance.subtract(amount));
        return update(account, account.getId());
    }
}
