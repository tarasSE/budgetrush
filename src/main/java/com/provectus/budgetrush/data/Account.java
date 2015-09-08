package com.provectus.budgetrush.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @JsonProperty("Account id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;

    @JsonProperty("User")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("Currency")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currency_id")
    private Currency currency;

}