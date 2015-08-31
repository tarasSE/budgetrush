package com.provectus.budgetrush.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = { "id" })
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private @Getter @Setter int id;

    @Column(name = "name")
    private @Getter @Setter String name;

    @Column(name = "pass")
    private @Getter @Setter String password;

}
