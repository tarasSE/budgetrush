package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Autowired
    private TransferOrderRepository transferOrderRepository;

    @Autowired
    OrderService orderService;

    public TransferOrder transfer(TransferOrder transfer) {
        Order expense = new Order();
        Order income = new Order();

        log.info(transfer.toString());
        expense.setAccount(transfer.getAccount());
        expense.setAmount(transfer.getAmount().negate());

        income.setAccount(transfer.getTransferAccount());
        income.setAmount(transfer.getAmount());

        transfer.setExpense(orderService.create(expense));
        transfer.setIncome(orderService.create(income));

        return create(transfer);
    }

    @Override
    protected TransferOrderRepository getRepository() {
        return transferOrderRepository;
    }
}
