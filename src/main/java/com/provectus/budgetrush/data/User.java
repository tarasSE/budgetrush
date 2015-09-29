package com.provectus.budgetrush.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Length(min = 4, max = 20)
    private String name;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @JsonIgnore
    private Roles role;

}