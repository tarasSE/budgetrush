package com.provectus.budgetrush.data.budget;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BudgetStatistic {

    private Budget budget;
    private BigDecimal amount;
    private BigDecimal balance;

}
