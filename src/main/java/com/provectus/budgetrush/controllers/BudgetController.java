package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.budget.Budget;
import com.provectus.budgetrush.data.budget.BudgetStatistic;
import com.provectus.budgetrush.service.BudgetService;
import com.provectus.budgetrush.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/budgets", headers = "Accept=application/json")
@RestController
public class BudgetController {

    @Inject
    private BudgetService budgetService;

    @Inject
    private GroupService groupService;

    @PostFilter("inGroupOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Budget> listAll() {
        log.info("Get all budgets");
        return budgetService.getAll();
    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Budget getById(@PathVariable Integer id) {
        log.info("Get budget by id " + id);
        return budgetService.getById(id);
    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/statistics",method = GET)
    @ResponseBody
    public List<BudgetStatistic>  getAllBudgetStatistics() {
        log.info("Get all budget statistics");
        return budgetService.getAllBudgetStatistics();
    }

    @PostAuthorize("inGroupOrAdmin(#budget, 'read')")
    @RequestMapping(value = "/{id}/statistics",method = GET)
    @ResponseBody
    public BudgetStatistic getBudgetStatistic(@PathVariable int id) {
    	
    	Budget budget = budgetService.getById(id);
        log.info("Get budget statistic");
        return budgetService.getBudgetStatistic(budget);
    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(#budget.getGroup().getId()), 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Budget create(@RequestBody Budget budget) {
        log.info("Create budget");

        return budgetService.create(budget);
    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(@budgetService.getById(#id).getGroup().getId()), 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Budget update(@RequestBody Budget budget, @PathVariable Integer id) {
        log.info("update budget id " + id);

        return budgetService.update(budget, id);
    }

    @PreAuthorize("inGroupOrAdmin(@budgetService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete budget by id " + id);
        budgetService.delete(id);
    }

}
