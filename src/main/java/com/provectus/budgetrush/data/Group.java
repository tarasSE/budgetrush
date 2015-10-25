package com.provectus.budgetrush.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "users", callSuper = true)
@ToString(exclude = "users")
@Data
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_groups", joinColumns = { @JoinColumn(name = "group_id") },
               inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;
}
