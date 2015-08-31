package com.provectus.budgetrush.services;

import java.util.List;

import com.provectus.budgetrush.data.TransferOrder;

public interface TransferOrderService {

    TransferOrder addTransferOrder(TransferOrder transferOrder);

    void delete(int id);

    TransferOrder getByID(int id);

    TransferOrder editTransferOrder(TransferOrder transferOrder);

    List<TransferOrder> getAll();
}
