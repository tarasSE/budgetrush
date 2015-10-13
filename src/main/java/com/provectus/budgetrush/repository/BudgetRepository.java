package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Budget findByName(String name);
}