package com.provectus.budgetrush.dateprocessor;

import com.provectus.budgetrush.data.PeriodsEnum;

public interface DateProcessor {

    public Period createPeriod(PeriodsEnum periods, String startDate, String endDate);

}
