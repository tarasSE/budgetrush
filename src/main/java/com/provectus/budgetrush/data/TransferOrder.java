package com.provectus.budgetrush.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by taras on 27.08.15.
 */
@Data
@Entity
@Table(name = "orders")
@DiscriminatorValue("transfer")
@EqualsAndHashCode(callSuper = false)
public class TransferOrder extends Order {

    @Column(name = "expense_id")
    private int expenseId;

    @Column(name = "income_id")
    private int incomeId;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();
}
