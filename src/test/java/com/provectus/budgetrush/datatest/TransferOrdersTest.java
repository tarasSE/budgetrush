package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.order.OrderType;
import com.provectus.budgetrush.data.order.TransferOrder;
import com.provectus.budgetrush.service.*;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.Date;

import static java.math.BigDecimal.valueOf;

@ContextConfiguration(classes = {
        TransferOrderService.class,
        OrderService.class,
        AccountService.class,
        ContractorService.class,
        CategoryService.class
})

public class TransferOrdersTest extends TestGenericService<TransferOrder, TransferOrderService> {
    @Inject
    private TransferOrderService transferService;

    @Inject
    private AccountService accountService;

    @Inject
    private ContractorService contractorService;

    @Inject
    private CategoryService categoryService;

    @Override
    protected TransferOrder getEntity() {
        TransferOrder transferOrder = new TransferOrder();

        transferOrder.setId(3);
        transferOrder.setAmount(valueOf(999));
        transferOrder.setDate(new Date());
        transferOrder.setAccount(accountService.getById(1));
        transferOrder.setTransferAccount(accountService.getById(2));
        transferOrder.setCategory(categoryService.getById(1));
        transferOrder.setContractor(contractorService.getById(1));
        transferOrder.setType(OrderType.TRANSFER_ORDER);

        return transferOrder;
    }

    protected TransferOrderService getService() {
        return transferService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}