package com.provectus.budgetrush.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.OrderStatistic;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT NEW com.provectus.budgetrush.data.OrderStatistic("
            + "o.account, o.contractor, o.category, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "GROUP BY o.account, o.contractor, o.category")
    public List<OrderStatistic> getTurnoverByAccount(@Param("account_id") int accountId,
                                                     @Param("start_date") Date startDate,
                                                     @Param("end_date") Date endDate);

    @Query("SELECT NEW com.provectus.budgetrush.data.OrderStatistic("
            + "o.account, o.contractor, o.category, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount > 0.0 "
            + "GROUP BY o.account, o.contractor, o.category")
    public List<OrderStatistic> getIncomeByAccount(@Param("account_id") int accountId,
                                                   @Param("start_date") Date startDate,
                                                   @Param("end_date") Date endDate);

    @Query("SELECT NEW com.provectus.budgetrush.data.OrderStatistic("
            + "o.account, o.contractor, o.category, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account.id = :account_id "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount < 0.0 "
            + "GROUP BY o.account, o.contractor, o.category")
    public List<OrderStatistic> getExpenseByAccount(@Param("account_id") int accountId,
                                                    @Param("start_date") Date startDate,
                                                    @Param("end_date") Date endDate);

    @Query("SELECT NEW com.provectus.budgetrush.data.OrderStatistic("
            + "o.account, o.contractor, o.category, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.category = :category "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "AND o.amount < 0.0 "
            + "GROUP BY o.account, o.contractor, o.category")
    List<OrderStatistic> getExpenseByCategory(@Param("category") Category category,
                                              @Param("start_date") Date startDate,
                                              @Param("end_date") Date endDate);

    List<Order> findByDateBetween(Date startDate, Date endDate);
}
