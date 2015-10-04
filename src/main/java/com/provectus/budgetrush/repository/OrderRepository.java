package com.provectus.budgetrush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Account;
import com.provectus.budgetrush.data.AmountMovement;
import com.provectus.budgetrush.data.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT NEW com.provectus.budgetrush.data.AmountMovement("
            + "o.account, o.contractor, o.category, SUM(o.amount)) "
            + "FROM Order o "
            + "WHERE o.account = :account "
            + "AND o.date BETWEEN :start_date AND :end_date "
            + "GROUP BY o.account, o.contractor, o.category")
    public List<AmountMovement> getAmountMovementsByAccount(@Param("account") Account account,
                                                            @Param("start_date") Date startDate,
                                                            @Param("end_date") Date endDate);
}
