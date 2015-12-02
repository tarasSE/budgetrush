package com.provectus.budgetrush.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.AccountStatistic;
import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.repository.AccountRepository;

@Slf4j
@Service
@Repository
@Transactional(readOnly = true)
public class AccountService extends GenericService<Account, AccountRepository> {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	protected AccountRepository getRepository() {
		return accountRepository;
	}

	@Override
	@Transactional
	public Account create(Account account) {
		account.setBalance(new BigDecimal(0));
		return super.create(account);
	}

	@Override
	@Transactional
	public Account update(Account account, int id) {
		if (account.getBalance() == null) {
			account.setBalance(accountRepository.getOne(id).getBalance());
		}
		return super.update(account, id);
	}

	@Transactional
	public Account incressBalance(@NotNull Account account, BigDecimal amount) {
		BigDecimal balance = account.getBalance();
		if (balance == null) {
			balance = new BigDecimal(0);
		}

		account.setBalance(balance.add(amount));
		return update(account, account.getId());
	}

	@Transactional
	public Account decreaseBalance(Account account, BigDecimal amount) {
		BigDecimal balance = account.getBalance();
		if (balance == null) {
			balance = new BigDecimal(0);
		}
		account.setBalance(balance.subtract(amount));
		return update(account, account.getId());
	}

	public List<AccountStatistic> getTurnoverByAccount(int accountId,
			Date startDate, Date endDate) {
		return getRepository().getTurnoverByAccount(accountId, startDate,
				endDate);
	}

	public List<AccountStatistic> getIncomeByAccount(int accountId,
			Date startDate, Date endDate) {
		return getRepository()
				.getIncomeByAccount(accountId, startDate, endDate);
	}

	public List<AccountStatistic> getExpenseByAccount(int accountId,
			Date startDate, Date endDate) {
		return getRepository().getExpenseByAccount(accountId, startDate,
				endDate);
	}

	public List<AccountStatistic> getExpenseByCategoryAndGroup(Category category, Group group, 
															   Date startDate, 	  Date endDate) {
		
		return getRepository().getExpenseByCategoryAndGroup(category, group, startDate, endDate);
	}

}
