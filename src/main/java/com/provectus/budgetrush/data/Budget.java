package com.provectus.budgetrush.data;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "budgets")
public class Budget extends BaseEntity {

    private String name;

    private BigDecimal amount;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn
    Category category;

    @ManyToOne
    @JoinColumn
    private User user;


}
