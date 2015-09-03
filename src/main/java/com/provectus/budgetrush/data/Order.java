package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Use enumeration as discriminator
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "0")
public class Order {

    @JsonProperty("Order id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("Amount")
    @Column(name = "amount")
    private double amount;

    @JsonProperty("Date")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @JsonProperty("Contractor")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @JsonProperty("Account")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonProperty("Category")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "category_id")
    private Category category;

}