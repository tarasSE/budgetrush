package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.TransferOrder;

import java.util.List;

public interface TransferOrderService {

    TransferOrder addTransferOrder(TransferOrder transferOrder);

    void delete(int id);

    TransferOrder getByID(int id);

    TransferOrder editTransferOrder(TransferOrder transferOrder);

    List<TransferOrder> getAll();
}
