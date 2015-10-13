package com.provectus.budgetrush.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "users_groups",
               joinColumns = { @JoinColumn(name = "user_id") },
               inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private Set<Group> groups;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

}