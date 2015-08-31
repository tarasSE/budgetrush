package com.provectus.budgetrush.services;

import java.util.List;

import com.provectus.budgetrush.data.Contractor;

public interface ContractorService {

    void delete(int id);

    Contractor getByName(String name);

    Contractor getByID(int id);

    List<Contractor> getAll();

    Contractor editContractor(Contractor contractor);

    Contractor addContractor(Contractor contractor);

}
