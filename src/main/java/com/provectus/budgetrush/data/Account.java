package com.provectus.budgetrush.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

}