package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

public class TransferOrderServiceBean implements TransferOrderService {

    @Autowired
    private TransferOrderRepository transferOrderRepository;

    @Override
    public TransferOrder addTransferOrder(TransferOrder transferOrder) {
        return transferOrderRepository.saveAndFlush(transferOrder);
    }

    @Override
    public void delete(int id) {
        transferOrderRepository.delete(id);
    }

    @Override
    public TransferOrder getByID(int id) {
        return transferOrderRepository.getOne(id);
    }

    @Override
    public TransferOrder editTransferOrder(TransferOrder transferOrder) {
        return transferOrderRepository.saveAndFlush(transferOrder);
    }

    @Override
    public List<TransferOrder> getAll() {
        return transferOrderRepository.findAll();
    }

}
