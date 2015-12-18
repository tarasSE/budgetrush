package com.provectus.budgetrush.data.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.Roles;
import com.provectus.budgetrush.data.group.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = "groups", callSuper = true)
@ToString(exclude = "groups")
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotEmpty
    @Length(min = 4, max = 20)
    private String name;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JsonIgnore
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