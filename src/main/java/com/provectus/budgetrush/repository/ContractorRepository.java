package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    Contractor findByName(String name);
}
