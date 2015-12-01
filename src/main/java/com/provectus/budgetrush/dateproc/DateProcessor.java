package com.provectus.budgetrush.dateproc;

import com.provectus.budgetrush.data.DateType;
import org.joda.time.DateTime;

public interface DateProcessor {

    public void createPeriod(DateType dateType, String startDate, String endDate);

    public DateTime getStartDate();

    public DateTime getEndDate();
}
