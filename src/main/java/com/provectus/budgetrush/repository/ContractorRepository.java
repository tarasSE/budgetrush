package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
    Contractor findByName(String name);
}
