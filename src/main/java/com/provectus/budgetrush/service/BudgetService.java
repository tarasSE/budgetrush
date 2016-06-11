package com.provectus.budgetrush.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.budget.Budget;
import com.provectus.budgetrush.data.budget.BudgetStatistic;
import com.provectus.budgetrush.data.account.AccountStatistic;
import com.provectus.budgetrush.repository.BudgetRepository;

@Service
@Transactional(readOnly = true)
public class BudgetService extends GenericService<Budget, BudgetRepository> {

    @Inject
    private BudgetRepository budgetRepository;

    @Inject
    private AccountService accountService;

    @Override
    protected BudgetRepository getRepository() {
        return budgetRepository;
    }

    public BudgetStatistic getBudgetStatistic(Budget budget) {

        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal balance = BigDecimal.ZERO;

        List<AccountStatistic> statistics = accountService.getExpenseByCategoryAndGroup(budget.getCategory(),
        		budget.getGroup(),
                budget.getStartDate(),
                budget.getEndDate());

        for (AccountStatistic statistic : statistics) {
            amount = amount.add(statistic.getAmount());
        }

        balance = budget.getAmount().add(amount);
        return new BudgetStatistic(budget, amount, balance);
    }

    public List<BudgetStatistic> getAllBudgetStatistics() {
        List<BudgetStatistic> budgetStatistics = new ArrayList<>();
        for (Budget budget : getAll()) {
            budgetStatistics.add(getBudgetStatistic(budget));
        }

        return budgetStatistics;
    }
}
