package com.provectus.budgetrush.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@DiscriminatorValue("1")
public class TransferOrder extends Order {

    @Enumerated(EnumType.ORDINAL)
    @Column(insertable = false, updatable = false)
    private OrderType type;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Order expense;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Order income;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Account transferAccount;
}
