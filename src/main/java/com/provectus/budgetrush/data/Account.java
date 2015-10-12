package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

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

    @Min(value = 0)
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