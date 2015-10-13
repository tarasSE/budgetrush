package com.provectus.budgetrush.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    private String name;
}
