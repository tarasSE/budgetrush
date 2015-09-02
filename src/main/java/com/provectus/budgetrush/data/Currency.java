package com.provectus.budgetrush.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "currencies")
public class Currency {

    @JsonProperty("Currency id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;

    @JsonProperty("Short name")
    @Column(name = "short_name")
    private String shortName;

    @JsonProperty("Code")
    @Column(name = "code")
    private int code;

    @JsonProperty("Symbol")
    @Column(name = "symbol")
    private char symbol;
}