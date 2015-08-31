package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.data.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    public Contractor findByName(String name);
}
