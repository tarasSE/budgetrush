package com.provectus.budgetrush.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Created by taras on 27.08.15.
 */
@Data
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "order")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private String amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "contractor_id")
    private String contractorId;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Order contractorOrder;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Order accountOrder;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Order categoryOrder;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private Order expenseOrder;

    @ManyToOne
    @JoinColumn(name = "income_id", nullable = false)
    private Order incomeOrder;

}
