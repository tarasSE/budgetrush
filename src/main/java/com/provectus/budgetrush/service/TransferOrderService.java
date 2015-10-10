package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

import java.math.BigDecimal;

@Service
@Transactional
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Autowired
    private TransferOrderRepository transferOrderRepository;
@Autowired
private AccountRepository accountRepository;


    public  TransferOrder transfer(TransferOrder transfer){
        Order expense = new Order();
        Order income = new Order();

        expense.setAccount(getAccountRepository().findOne(1));
        income.setAccount(getAccountRepository().findOne(1));

        expense.setAmount(transfer.getAmount().multiply(BigDecimal.valueOf(-1.0)));
        income.setAmount(transfer.getAmount());

        return getRepository().saveAndFlush(transfer);
    }


    @Override
    protected TransferOrderRepository getRepository() {
        return transferOrderRepository;
    }

    protected AccountRepository getAccountRepository() {
        return accountRepository;
    }
}
