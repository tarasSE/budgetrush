package com.provectus.budgetrush.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "users", callSuper = true)
@ToString(exclude = "users")
@Data
@Entity
@Table(name = "groups")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "g_key")
public class Group extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "users_groups", joinColumns = { @JoinColumn(name = "group_id") },
               inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;
}
