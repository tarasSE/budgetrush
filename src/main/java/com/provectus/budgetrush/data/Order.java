package com.provectus.budgetrush.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Use enumeration as discriminator
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "0")
public class Order extends BaseEntity {

    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    @Column(insertable = false, updatable = false)
    private OrderType type;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Contractor contractor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")//todo
    private Category category;

}