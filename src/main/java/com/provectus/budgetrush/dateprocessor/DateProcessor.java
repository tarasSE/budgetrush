package com.provectus.budgetrush.dateprocessor;

import com.provectus.budgetrush.data.Periods;
import org.joda.time.DateTime;

public interface DateProcessor {

    public void createPeriod(Periods periods, String startDate, String endDate);

    public DateTime getStartDate();

    public DateTime getEndDate();
}
