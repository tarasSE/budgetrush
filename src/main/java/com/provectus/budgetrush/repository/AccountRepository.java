package com.provectus.budgetrush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.AccountStatistic;
import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Group;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByName(String name);
    
    @Query("SELECT NEW com.provectus.budgetrush.data.AccountStatistic("
            + "o.account, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "GROUP BY o.account")
    public List<AccountStatistic> getTurnoverByAccount(@Param("account_id") int accountId,
                                                     @Param("start_date") Date startDate,
                                                     @Param("end_date") Date endDate);

    @Query("SELECT NEW com.provectus.budgetrush.data.AccountStatistic("
            + "o.account, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount > 0.0 "
            + "GROUP BY o.account")
    public List<AccountStatistic> getIncomeByAccount(@Param("account_id") int accountId,
                                                   @Param("start_date") Date startDate,
                                                   @Param("end_date") Date endDate);

    @Query("SELECT NEW com.provectus.budgetrush.data.AccountStatistic("
            + "o.account,SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount < 0.0 "
            + "GROUP BY o.account")
    public List<AccountStatistic> getExpenseByAccount(@Param("account_id") int accountId,
                                                    @Param("start_date") Date startDate,
                                                    @Param("end_date") Date endDate);
    
    @Query("SELECT NEW com.provectus.budgetrush.data.AccountStatistic("
            + "o.account, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.category = :category "
            + "AND o.account.group = :group "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount < 0.0 "
            + "GROUP BY o.account")
    List<AccountStatistic> getExpenseByCategoryAndGroup(@Param("category") Category category,
    													@Param("group") Group group,
		                                                @Param("start_date") Date startDate,
		                                                @Param("end_date") Date endDate);
}