package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.FilterEnum;
import com.provectus.budgetrush.data.PeriodsEnum;
import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.account.AccountStatistic;
import com.provectus.budgetrush.dateprocessor.DateProcessor;
import com.provectus.budgetrush.dateprocessor.Period;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(value = "/v1/accounts", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class AccountController {

    @Inject
    private AccountService accountService;

    @Inject
    private GroupService groupService;

    @Inject
    private DateProcessor dateProcessor;

    @PostFilter("inGroupOrAdmin(filterObject.group, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    @Transactional
    public List<Account> listAll() {
        log.info("Get all accounts");
        List<Account> accounts = accountService.getAll();
        log.info("Send all accounts " + accounts.size());
        return accounts;
    }

    @PostAuthorize("inGroupOrAdmin(returnObject.group, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Account getById(@PathVariable Integer id) {
        log.info("Get account by id " + id);
        Account account = accountService.getById(id);
        log.info("Send account " + account.getName());

        return account;

    }

    @PreAuthorize("inGroupOrAdmin(@accountService.getById(#id).group, 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete account by id " + id);
        accountService.delete(id);
    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(#account.getGroup().getId()), 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Account newAccount(@RequestBody Account account) {
        log.info("Save account " + account.getName());
        return accountService.create(account);

    }

    @PreAuthorize("inGroupOrAdmin(@accountService.getById(#id).group, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Account changeAccount(@RequestBody Account account,
                               @PathVariable Integer id) {
        log.info("Change account " + account.getName());
        Account result = accountService.update(account, id);
        log.info(result.toString());
        return result;
    }

    @PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
    @RequestMapping(value = "statistics", method = GET)
    @ResponseBody
    public List<AccountStatistic> getTurnover(@RequestParam int accountId,
                                              @RequestParam PeriodsEnum period,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @RequestParam(required = false) FilterEnum filter) {

        Period createdPeriod = dateProcessor.createPeriod(period, startDate, endDate);

        if (filter == FilterEnum.INCOME) {

            log.info("Get account income by period.");

            return accountService.getIncomeByAccount(
                    accountId,
                    createdPeriod.getStartDate(),
                    createdPeriod.getEndDate());
        }

        if (filter == FilterEnum.EXPENSE) {

            log.info("Get account expense by period.");

            return accountService.getExpenseByAccount(
                    accountId,
                    createdPeriod.getStartDate(),
                    createdPeriod.getEndDate());
        }

        log.info("Get account turnover by period.");

        return accountService.getIncomeByAccount(
                accountId,
                createdPeriod.getStartDate(),
                createdPeriod.getEndDate());

    }

//    @PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
//    @RequestMapping(value = "statistics/income", method = GET)
//    @ResponseBody
//    public List<AccountStatistic> getIncome(@RequestParam int accountId,
//                                            @RequestParam PeriodsEnum period,
//                                            @RequestParam(required = false) String startDate,
//                                            @RequestParam(required = false) String endDate) {
//
//        Period createdPeriod = dateProcessor.createPeriod(period, startDate,
//                endDate);
//        log.info("Get account incom by period.");
//
//        return accountService.getIncomeByAccount(accountId,
//                createdPeriod.getStartDate(), createdPeriod.getEndDate());
//    }
//
//    @PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
//    @RequestMapping(value = "statistics/expense", method = GET)
//    @ResponseBody
//    public List<AccountStatistic> getExpense(@RequestParam int accountId,
//                                             @RequestParam PeriodsEnum period,
//                                             @RequestParam(required = false) String startDate,
//                                             @RequestParam(required = false) String endDate) {
//
//        Period createdPeriod = dateProcessor.createPeriod(period, startDate, endDate);
//        log.info("Get account expense by period.");
//
//        return accountService.getExpenseByAccount(accountId, createdPeriod.getStartDate(), createdPeriod.getEndDate());
//    }
}
