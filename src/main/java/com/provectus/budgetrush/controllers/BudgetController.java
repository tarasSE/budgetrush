package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.Budget;
import com.provectus.budgetrush.data.BudgetStatistic;
import com.provectus.budgetrush.service.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/budgets", headers = "Accept=application/json")
@RestController
public class BudgetController {

    @Autowired
    private BudgetService service;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Budget> listAll() {
        log.info("Get all budgets");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Budget getById(@PathVariable Integer id) {
        log.info("Get budget by id " + id);
        return service.getById(id);
    }

    @PostFilter("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/statistics",method = GET)
    @ResponseBody
    public List<BudgetStatistic>  getAllBudgetStatistics() {
        log.info("Get all budget statistics");
        return service.getAllBudgetStatistics();
    }

    //@PostFilter("isObjectOwnerOrAdmin(returnObject, 'read')") //todo
    @RequestMapping(value = "/{id}/statistics",method = GET)
    @ResponseBody
    public BudgetStatistic getBudgetStatistic(@PathVariable int id) {

        Budget budget = service.getById(id);

        log.info("Get budget statistic");
        return service.getBudgetStatistic(budget);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#budget, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Budget create(@RequestBody Budget budget) {
        log.info("Create budget");

        return service.create(budget);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#budget, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Budget update(@RequestBody Budget budget, @PathVariable Integer id) {
        log.info("update budget id " + id);

        return service.update(budget, id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@budgetService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete budget by id " + id);
        service.delete(id);
    }

}
