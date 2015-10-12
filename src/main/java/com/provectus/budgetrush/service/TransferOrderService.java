package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Autowired
    private TransferOrderRepository transferOrderRepository;

    @Autowired
    OrderService orderService;

    @Override
    public boolean delete(int id) {
        TransferOrder transferOrder = getById(id);

        Order income = transferOrder.getIncome();

        BigDecimal incomeAmount = income.getAmount();
        Account incomeAccount = income.getAccount();
        incomeAccount.setBalance(incomeAccount.getBalance().add(incomeAmount.negate()));

        Order expense = transferOrder.getExpense();
        Account expenseAccount = expense.getAccount();
        BigDecimal expenseAmount = expense.getAmount();
        expenseAccount.setBalance(expenseAccount.getBalance().add(expenseAmount.negate()));

        log.info("Delete and return back income order");
        orderService.delete(income.getId());
        log.info("Delete and return back expense order");
        orderService.delete(expense.getId());
        log.info("Delete transfer order");
        super.delete(id);


        return true;
    }

    public TransferOrder transfer(TransferOrder transfer) {
        Order expense = new Order();
        Order income = new Order();


        expense.setAccount(transfer.getAccount());
        expense.setAmount(transfer.getAmount().negate());
        expense=orderService.create(expense);

        income.setAccount(transfer.getTransferAccount());
        income.setAmount(transfer.getAmount());
        income=orderService.create(income);

        transfer.setExpense(expense);
        transfer.setIncome(income);
        log.info("Create transfer");
        return create(transfer);
    }

    @Override
    protected TransferOrderRepository getRepository() {
        return transferOrderRepository;
    }
}
