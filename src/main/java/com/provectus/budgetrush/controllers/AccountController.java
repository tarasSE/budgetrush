package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.AccountStatistic;
import com.provectus.budgetrush.data.PeriodsEnum;
import com.provectus.budgetrush.dateprocessor.DateProcessor;
import com.provectus.budgetrush.dateprocessor.Period;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.GroupService;

@Slf4j
@RequestMapping(value = "/v1/accounts", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private GroupService groupService;

	@Autowired
	private DateProcessor dateProcessor;

	@PostFilter("inGroupOrAdmin(filterObject.group, 'read')")
	@RequestMapping(method = GET)
	@ResponseBody
	@Transactional
	public List<Account> listAll() {
		log.info("Get all accounts");
		List<Account> accounts = service.getAll();
		log.info("Send all accounts " + accounts.size());
		return accounts;
	}

	@PostAuthorize("inGroupOrAdmin(returnObject.group, 'read')")
	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Account getById(@PathVariable Integer id) {
		log.info("Get account by id " + id);
		Account account = service.getById(id);
		log.info("Send account " + account.getName());

		return account;

	}

	@PreAuthorize("inGroupOrAdmin(@accountService.getById(#id).group, 'delete')")
	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		log.info("Delete account by id " + id);
		service.delete(id);
	}

	@PreAuthorize("inGroupOrAdmin(@groupService.getById(#account.getGroup().getId()), 'write')")
	@RequestMapping(method = POST)
	@ResponseBody
	public Account newUser(@RequestBody Account account) {
		log.info("Save account " + account.getName());
		return service.create(account);

	}

	@PreAuthorize("inGroupOrAdmin(@accountService.getById(#id).group, 'write')")
	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public Account saveUser(@RequestBody Account account,
			@PathVariable Integer id) {
		log.info("Save account " + account.getName());
		Account result = service.update(account, id);
		log.info(result.toString());
		return result;
	}

	@PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
	@RequestMapping(value = "statistics/turnover", method = GET)
	@ResponseBody
	public List<AccountStatistic> getTurnover(@RequestParam int accountId,
											  @RequestParam PeriodsEnum period,
											  @RequestParam(required = false) String startDate,
										      @RequestParam(required = false) String endDate) {
									
		Period createdPeriod = dateProcessor.createPeriod(period, startDate, endDate);
		log.info("Get account turnover by period.");
									
		return service.getIncomeByAccount(accountId, createdPeriod.getStartDate(), createdPeriod.getEndDate());

	}

	@PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
	@RequestMapping(value = "statistics/income", method = GET)
	@ResponseBody
	public List<AccountStatistic> getIncome(@RequestParam int accountId,
											@RequestParam PeriodsEnum period,
											@RequestParam(required = false) String startDate,
											@RequestParam(required = false) String endDate) {

		Period createdPeriod = dateProcessor.createPeriod(period, startDate,
				endDate);
		log.info("Get account incom by period.");

		return service.getIncomeByAccount(accountId,
				createdPeriod.getStartDate(), createdPeriod.getEndDate());
	}

	@PostFilter("inGroupOrAdmin(filterObject.account.group, 'read')")
	@RequestMapping(value = "statistics/expense", method = GET)
	@ResponseBody
	public List<AccountStatistic> getExpense(@RequestParam int accountId,
											 @RequestParam PeriodsEnum period,
											 @RequestParam(required = false) String startDate,
											 @RequestParam(required = false) String endDate) {

		Period createdPeriod = dateProcessor.createPeriod(period, startDate, endDate);
		log.info("Get account expense by period.");

		return service.getExpenseByAccount(accountId, createdPeriod.getStartDate(), createdPeriod.getEndDate());
	}
}
