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

/**
 * Created by taras on 27.08.15.
 */

@Entity
@Table(name = "contractors")
public class Contractor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "contractors", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();
}
