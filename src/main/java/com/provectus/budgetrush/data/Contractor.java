package com.provectus.budgetrush.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "contractors")
public class Contractor extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private User user;

}
