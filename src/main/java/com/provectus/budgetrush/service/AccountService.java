package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.account.AccountStatistic;
import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.data.group.Group;
import com.provectus.budgetrush.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Repository
@Transactional(readOnly = true)
public class AccountService extends GenericService<Account, AccountRepository> {

    @Inject
    private AccountRepository accountRepository;

    @Override
    protected AccountRepository getRepository() {
        return accountRepository;
    }

    @Override
    @Transactional
    public Account create(Account account) {
        if (account.getInitBalance() == null){
            account.setInitBalance(new BigDecimal(0));
        }

        account.setCurrentBalance(account.getInitBalance());
        return super.create(account);
    }

    @Override
    @Transactional
    public Account update(Account account, int id) {

        account.setId(id);

        if (account.getInitBalance() == null){
            account.setInitBalance(accountRepository.getOne(id).getInitBalance());
        }

        if (account.getCurrentBalance() == null) {
            account.setCurrentBalance(accountRepository.getOne(id).getCurrentBalance());
        }

        BigDecimal newInitBalance = account.getInitBalance();
        BigDecimal oldInitBalance = accountRepository.getOne(id).getInitBalance();

        if (account.getInitBalance() != null && !newInitBalance.equals(oldInitBalance)) {

            increaseBalance(account, newInitBalance.subtract(oldInitBalance));
        }
        return super.update(account, id);
    }

    @Transactional
    public Account increaseBalance(@NotNull Account account, BigDecimal amount) {
        BigDecimal balance = account.getCurrentBalance();
        if (balance == null) {
            balance = new BigDecimal(0);
        }

        account.setCurrentBalance(balance.add(amount));
        return super.update(account, account.getId());
    }

    @Transactional
    public Account decreaseBalance(Account account, BigDecimal amount) {
        BigDecimal balance = account.getCurrentBalance();
        if (balance == null) {
            balance = new BigDecimal(0);
        }
        account.setCurrentBalance(balance.subtract(amount));
        return update(account, account.getId());
    }

    public List<AccountStatistic> getTurnoverByAccount(int accountId,
                                                       Date startDate, Date endDate) {
        Assert.isTrue(accountId != 0, "Account id can`t be null");
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        return getRepository().getTurnoverByAccount(accountId, startDate,
                endDate);
    }

    public List<AccountStatistic> getIncomeByAccount(int accountId,
                                                     Date startDate, Date endDate) {

        Assert.isTrue(accountId != 0, "Account id can`t be null");
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        return getRepository()
                .getIncomeByAccount(accountId, startDate, endDate);
    }

    public List<AccountStatistic> getExpenseByAccount(int accountId,
                                                      Date startDate, Date endDate) {

        Assert.isTrue(accountId != 0, "Account id can`t be null");
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        return getRepository().getExpenseByAccount(accountId, startDate,
                endDate);
    }

    public List<AccountStatistic> getExpenseByCategoryAndGroup(Category category, Group group,
                                                               Date startDate, Date endDate) {

        Assert.notNull(category, "Category can`t be null");
        Assert.notNull(group, "Group can`t be null");
        Assert.notNull(startDate, "Start date can`t be null");
        Assert.notNull(endDate, "End date can`t be null");
        Assert.isTrue(endDate.after(startDate), "End date must be after start date");

        return getRepository().getExpenseByCategoryAndGroup(category, group, startDate, endDate);
    }

}
