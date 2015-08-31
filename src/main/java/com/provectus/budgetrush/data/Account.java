package com.provectus.budgetrush.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@EqualsAndHashCode(exclude = { "id" })
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private @Getter @Setter int id;

    @Column(name = "name")
    private @Getter @Setter String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "user_id")
    private @Getter @Setter User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "currency_id")
    private @Getter @Setter Currency currency;

}
