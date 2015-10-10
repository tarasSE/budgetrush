package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Budget;
import com.provectus.budgetrush.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetService extends GenericService<Budget, BudgetRepository> {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    protected BudgetRepository getRepository() {
        return budgetRepository;
    }
}
