package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @JsonProperty("User id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("Name")
    @Column(name = "name")
    private String name;

    @JsonProperty("Password")
    @Column(name = "pass")
    private String password;

}