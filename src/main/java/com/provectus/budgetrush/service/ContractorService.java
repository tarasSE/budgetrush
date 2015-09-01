package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Contractor;

import java.util.List;

public interface ContractorService {

    void delete(int id);

    Contractor getByName(String name);

    Contractor getByID(int id);

    List<Contractor> getAll();

    Contractor editContractor(Contractor contractor);

    Contractor addContractor(Contractor contractor);

}
