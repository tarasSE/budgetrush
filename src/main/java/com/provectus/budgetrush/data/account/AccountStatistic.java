package com.provectus.budgetrush.data.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountStatistic {

    private Account account;
    private BigDecimal amount;

}
