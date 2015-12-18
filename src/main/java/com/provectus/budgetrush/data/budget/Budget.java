package com.provectus.budgetrush.data.budget;

import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.data.group.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "budgets")
public class Budget extends BaseEntity {

    private String name;

    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn
    private Group group;

}
