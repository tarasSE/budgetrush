package com.provectus.budgetrush.data;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Currency currency;

    private BigDecimal balance;

    @JsonProperty
    public BigDecimal getBalance() {
        return balance;
    }

    @JsonIgnore
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}