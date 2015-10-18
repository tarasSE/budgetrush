package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.repository.ContractorRepository;

@Service
@Repository
public class ContractorService extends GenericService<Contractor, ContractorRepository> {

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    protected ContractorRepository getRepository() {
        return contractorRepository;
    }
}
