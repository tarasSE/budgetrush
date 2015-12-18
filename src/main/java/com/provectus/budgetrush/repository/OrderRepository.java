package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.account.Account;
import com.provectus.budgetrush.data.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByDateBetween(Date startDate, Date endDate);

    List<Order> findByAccountAndDateBetweenAndAmountIsGreaterThanEqual(Account account,
                                                                       Date startDate,
                                                                       Date endDate,
                                                                       BigDecimal amount);

    List<Order> findByAccountAndDateBetweenAndAmountIsLessThan(Account account,
                                                               Date startDate,
                                                               Date endDate,
                                                               BigDecimal amount);

    List<Order> findByAccountAndDateBetween(Account account,
                                            Date startDate,
                                            Date endDate);
}
