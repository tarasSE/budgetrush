package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

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
    private String shortname;

    @JsonProperty("Code")
    @Column(name = "code")
    private int code;

    @JsonProperty("Symbol")
    @Column(name = "symbol")
    private char symbol;
}