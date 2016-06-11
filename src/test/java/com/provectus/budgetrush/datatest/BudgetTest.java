package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.budget.Budget;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.BudgetService;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.GroupService;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

@ContextConfiguration(classes = {
        BudgetService.class,
        CategoryService.class,
        GroupService.class,
        AccountService.class})
public class BudgetTest extends TestGenericService<Budget, BudgetService> {
    @Inject
    private BudgetService budgetService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private GroupService groupService;

    @Inject
    private AccountService accountService;

    @Override
    protected Budget getEntity() {
        Budget budget = new Budget();

        budget.setId(1);
        budget.setName("Budget");
        budget.setCategory(categoryService.getById(1));
        budget.setStartDate(new Date());
        budget.setEndDate(new Date());
        budget.setGroup(groupService.getById(1));
        budget.setAmount(BigDecimal.ONE);

        return budget;
    }

    protected BudgetService getService() {
        return budgetService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
