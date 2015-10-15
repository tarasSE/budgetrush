package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.budgetrush.data.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Budget findByName(String name);
}