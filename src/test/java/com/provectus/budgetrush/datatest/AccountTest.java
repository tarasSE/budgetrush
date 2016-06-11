package com.provectus.budgetrush.datatest;


import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.account.AccountStatistic;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.CurrencyService;
import com.provectus.budgetrush.service.GroupService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {
        AccountService.class,
        GroupService.class,
        CurrencyService.class})

public class AccountTest extends TestGenericService<Account, AccountService> {
    @Inject
    private AccountService accountService;

    @Inject
    private CurrencyService currencyService;

    @Inject
    private GroupService groupService;

    @Test
    public void getAmountMovement() {
        Account account = getEntityAndSave();

        Date startDate = new Date(0);
        Date endDate = new Date();

        List<AccountStatistic> amounts =
                accountService.getTurnoverByAccount(
                        account.getId(),
                        startDate,
                        endDate);

        assertNotNull(amounts);
    }

    @Test
    public void getIncomeByAccount() {
        Account account = getEntityAndSave();
        Date startDate = new Date(0);
        Date endDate = new Date();
        List<AccountStatistic> incomes =
                accountService.getIncomeByAccount(
                        account.getId(),
                        startDate,
                        endDate);

        assertNotNull(incomes);
    }

    @Test
    public void getExpenseByAccount() {
        Account account = getEntityAndSave();

        Date startDate = new Date(0);
        Date endDate = new Date();

        List<AccountStatistic> expenses =
                accountService.getExpenseByAccount(
                        account.getId(),
                        startDate,
                        endDate);

        assertNotNull(expenses);
    }

    @Override
    protected Account getEntity() {
        Account account = new Account();

        account.setName(Integer.toString(random.nextInt()));
        account.setGroup(groupService.getById(1));
        account.setCurrency(currencyService.getById(1));

        return account;
    }

    protected AccountService getService() {
        return accountService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
