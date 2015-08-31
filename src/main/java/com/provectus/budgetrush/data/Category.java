package com.provectus.budgetrush.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by taras on 27.08.15.
 */
@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent")
    private int parent;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();
}
