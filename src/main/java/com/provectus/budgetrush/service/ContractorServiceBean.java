package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.repository.ContractorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractorServiceBean implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;


    public Contractor addContractor(Contractor contractor) {
        log.info("Start to save contractor " + contractor.getName());
        return contractorRepository.saveAndFlush(contractor);
    }


    public void delete(int id) {
        log.info("Start to delete contractor (ID=" + id + ")");
        contractorRepository.delete(id);
    }


    public Contractor getByID(int id) {
        return contractorRepository.getOne(id);
    }


    public Contractor editContractor(Contractor contractor) {
        return contractorRepository.saveAndFlush(contractor);
    }


    public List<Contractor> getAll() {
        return contractorRepository.findAll();
    }


    public Contractor getByName(String name) {
        return contractorRepository.findByName(name);
    }

}
