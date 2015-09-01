package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TransferOrderServiceBean implements TransferOrderService {

    @Autowired
    private TransferOrderRepository transferOrderRepository;


    public TransferOrder addTransferOrder(TransferOrder transferOrder) {
        return transferOrderRepository.saveAndFlush(transferOrder);
    }


    public void delete(int id) {
        transferOrderRepository.delete(id);
    }


    public TransferOrder getByID(int id) {
        return transferOrderRepository.getOne(id);
    }


    public TransferOrder editTransferOrder(TransferOrder transferOrder) {
        return transferOrderRepository.saveAndFlush(transferOrder);
    }


    public List<TransferOrder> getAll() {
        return transferOrderRepository.findAll();
    }

}
