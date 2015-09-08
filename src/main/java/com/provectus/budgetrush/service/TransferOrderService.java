package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.repository.TransferOrderRepository;

@Service
@Transactional
public class TransferOrderService extends GenericService<TransferOrder, TransferOrderRepository> {

    @Autowired
    private TransferOrderRepository transferOrderRepository;

    @Override
    protected TransferOrderRepository getRepository() {
        return transferOrderRepository;
    }
}
