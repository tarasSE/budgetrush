package com.provectus.budgetrush.service;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.order.Order;
import com.provectus.budgetrush.data.order.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

@Slf4j
@Service
@Repository
@Transactional(readOnly = true)
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Inject
    private TransferOrderRepository transferOrderRepository;

    @Inject
    OrderService orderService;

    @Inject
    AccountService accountService;

    @Override
    protected TransferOrderRepository getRepository() {
        return transferOrderRepository;
    }

    @Override
    @Transactional
    public TransferOrder create(TransferOrder transfer) {
        Order expense = new Order();
        Order income = new Order();

        expense.setDate(transfer.getDate());
        expense.setAccount(transfer.getAccount());
        expense.setAmount(transfer.getAmount().negate());
        expense = orderService.create(expense);

        income.setDate(transfer.getDate());
        income.setAccount(transfer.getTransferAccount());
        income.setAmount(transfer.getAmount());
        income = orderService.create(income);

        transfer.setExpense(expense);
        transfer.setIncome(income);
        log.info("Create transfer");
        return super.create(transfer);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        TransferOrder oldTransfer = getById(id);

        accountService.decreaseBalance(oldTransfer.getTransferAccount(), oldTransfer.getAmount());
        accountService.increaseBalance(oldTransfer.getAccount(), oldTransfer.getAmount());

        return super.delete(id);
    }
}
