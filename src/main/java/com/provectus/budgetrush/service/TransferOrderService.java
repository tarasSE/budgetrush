package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Autowired
    private TransferOrderRepository transferOrderRepository;

    @Autowired OrderService orderService;

    public TransferOrder transfer(TransferOrder transfer) {
        Order expense = new Order();
        Order income = new Order();

        log.info(transfer.toString());
        expense.setAccount(transfer.getAccount());
        expense.setAmount(transfer.getAmount().multiply(BigDecimal.valueOf(-1.0)));

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
