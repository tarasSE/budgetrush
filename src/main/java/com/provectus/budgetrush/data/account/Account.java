package com.provectus.budgetrush.data.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.currency.Currency;
import com.provectus.budgetrush.data.group.Group;
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

    @Min(0)
    private BigDecimal currentBalance;

    @Min(0)
    private BigDecimal initBalance;

    @JsonProperty
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    @JsonIgnore
    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

}