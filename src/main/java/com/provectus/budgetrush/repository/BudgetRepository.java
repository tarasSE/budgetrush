package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Budget;

@Repository
@Transactional(readOnly = true)
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Budget findByName(String name);
}   
                                                     