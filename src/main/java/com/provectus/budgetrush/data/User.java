package com.provectus.budgetrush.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotEmpty
    @Length(min = 4, max = 20)
    private String name;

    @NotEmpty
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;

}