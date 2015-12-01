package com.provectus.budgetrush.dateprocessor;

import com.provectus.budgetrush.data.Periods;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Scope("request")
public class DateProcessorBean implements DateProcessor{

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private DateTime startDate;

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private DateTime endDate;

    public void createPeriod(Periods period, String startDate, String endDate) {

        switch (period) {

            case TODAY:
                setTodayPeriod();
                break;

            case YESTERDAY:
                setYesterdayPeriod();
                break;

            case LAST_WEEK:
                setLastWeekPeriod();
                break;

            case LAST_MONTH:
                setLastMonthPeriod();
                break;

            case LAST_YEAR:
                setLastYearPeriod();
                break;

            case CUSTOM:
                setCustomPeriod(startDate, endDate);
                break;
        }

    }

    private void setCustomPeriod(String startDate, String endDate) {
        setStartDate(DateTime.parse(startDate));
        setEndDate(DateTime.parse(endDate));
    }

    private void setLastYearPeriod() {
        setCurrentDateAsEndDate();

        DateTime startDate = new DateTime(getEndDate())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0);
        startDate = startDate.minusYears(1);

        setStartDate(startDate);
    }

    private void setLastMonthPeriod() {
        setCurrentDateAsEndDate();

        DateTime startDate = new DateTime(getEndDate())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0);

        startDate = startDate.minusMonths(1);
        setStartDate(startDate);
    }

    private void setLastWeekPeriod() {

        setCurrentDateAsEndDate();

        DateTime startDate = new DateTime(getEndDate())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0);

        startDate = startDate.minusDays(7);

        setStartDate(startDate);
    }

    private void setYesterdayPeriod() {

        DateTime endDate = new DateTime(new Date());
        endDate = endDate.minusDays(1);
        endDate = endDate
                .hourOfDay().setCopy(23)
                .minuteOfHour().setCopy(59)
                .secondOfMinute().setCopy(59);

        setEndDate(endDate);

        DateTime startDate = new DateTime(getEndDate())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0);

        startDate = startDate.minusDays(1);

        setStartDate(startDate);
    }

    private void setTodayPeriod() {
        DateTime startDate = new DateTime(getEndDate())
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0);

        setStartDate(startDate);
        setCurrentDateAsEndDate();
    }

    private void setCurrentDateAsEndDate() {
        setEndDate(DateTime.now());
    }

}
