package com.provectus.budgetrush.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatistic {

    private Account account;
    private Contractor contractor;
    private Category category;
    private BigDecimal amount;

}
