package com.provectus.budgetrush.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
@DiscriminatorValue("transfer")
@EqualsAndHashCode(callSuper = false)
public class TransferOrder extends Order {

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "expense_id", nullable = false , insertable = false, updatable = false)
    private Order expenseOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "income_id", nullable = false , insertable = false, updatable = false)
    private Order incomeOrder;
}
