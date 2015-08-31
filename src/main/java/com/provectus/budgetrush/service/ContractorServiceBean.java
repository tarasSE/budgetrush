package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.repository.ContractorRepository;

public class ContractorServiceBean implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    public Contractor addContractor(Contractor contractor) {
        return contractorRepository.saveAndFlush(contractor);
    }

    @Override
    public void delete(int id) {
        contractorRepository.delete(id);
    }

    @Override
    public Contractor getByID(int id) {
        return contractorRepository.getOne(id);
    }

    @Override
    public Contractor editContractor(Contractor contractor) {
        return contractorRepository.saveAndFlush(contractor);
    }

    @Override
    public List<Contractor> getAll() {
        return contractorRepository.findAll();
    }

    @Override
    public Contractor getByName(String name) {
        return contractorRepository.findByName(name);
    }

}
