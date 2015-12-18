package com.provectus.budgetrush.data.contractor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
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
