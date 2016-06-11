package com.provectus.budgetrush.service;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.contractor.Contractor;
import com.provectus.budgetrush.repository.ContractorRepository;

@Service
@Repository
@Transactional(readOnly = true)
public class ContractorService extends GenericService<Contractor, ContractorRepository> {

    @Inject
    private ContractorRepository contractorRepository;

    @Override
    protected ContractorRepository getRepository() {
        return contractorRepository;
    }
}
