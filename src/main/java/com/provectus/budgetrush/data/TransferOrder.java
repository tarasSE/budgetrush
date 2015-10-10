package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
@DiscriminatorValue("1")
public class TransferOrder extends Order {

    @Enumerated(EnumType.ORDINAL)
    @Column(insertable = false, updatable = false)
    private OrderType type;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Order expense;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Order income;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Account transferAccount;
}
