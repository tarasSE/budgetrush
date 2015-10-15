package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.budgetrush.data.Contractor;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    Contractor findByName(String name);
}
