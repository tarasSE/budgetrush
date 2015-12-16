package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Group group;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Currency currency;

    @Min(value = 0, message = "Balance can not be negative!")
    private BigDecimal balance;

    @Min(value = 0, message = "Balance can not be negative!")
    private BigDecimal initBalance;

    @JsonProperty
    public BigDecimal getBalance() {
        return balance;
    }

    @JsonIgnore
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}