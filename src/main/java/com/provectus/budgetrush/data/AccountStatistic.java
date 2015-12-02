package com.provectus.budgetrush.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountStatistic {

    private Account account;
    private BigDecimal amount;

}
