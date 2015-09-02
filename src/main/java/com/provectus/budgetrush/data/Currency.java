package com.provectus.budgetrush.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Use @Data annotation
@Entity
@Table(name = "currencies")
//Why do you exclude id field?
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
    private @Getter @Setter int code;

    @Column(name = "symbol")
    private @Getter @Setter char symbol;
}