package com.provectus.budgetrush.dateprocessor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Period {

    private Date startDate;

    private Date endDate;

}
