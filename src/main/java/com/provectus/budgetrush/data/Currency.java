package com.provectus.budgetrush.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    private String name;

    @Column(name = "short_name")
    private String shortName;

    private int code;

    private char symbol;
}