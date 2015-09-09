package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.repository.ContractorRepository;

@Service
@Transactional
public class ContractorService extends GenericService<Contractor, ContractorRepository> {

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    protected ContractorRepository getRepository() {
        return contractorRepository;
    }
}
