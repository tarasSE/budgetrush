package com.provectus.budgetrush.data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "groups", callSuper = true)
@ToString(exclude = "groups")
@Data
@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "u_key")
public class User extends BaseEntity {

    @NotEmpty
    @Length(min = 4, max = 20)
    private String name;

    @NotEmpty
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    // @JsonIgnore
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