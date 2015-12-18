package com.provectus.budgetrush.data.group;

import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = "users", callSuper = true)
@ToString(exclude = "users")
@Data
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_groups", joinColumns = { @JoinColumn(name = "group_id") },
               inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;
}
