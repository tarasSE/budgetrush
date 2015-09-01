package com.provectus.budgetrush.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currencies")
@EqualsAndHashCode(exclude = { "id" })
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private @Getter @Setter int id;

    @Column(name = "name")
    private @Getter @Setter String name;

    @Column(name = "short_name")
    private @Getter @Setter String shortname;

    @Column(name = "code")
    private @Getter @Setter String code;

    @Column(name = "symbol")
    private @Getter @Setter char symbol;
}
