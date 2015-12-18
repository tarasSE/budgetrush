package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.contractor.Contractor;

@Repository
@Transactional(readOnly = true)
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    Contractor findByName(String name);
}
