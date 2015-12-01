package com.provectus.budgetrush.dateprocessor;

import com.provectus.budgetrush.data.PeriodsEnum;
import com.provectus.budgetrush.exceptions.CustomException;
import org.joda.time.DateTime;

import java.util.Date;

public class DateProcessorBean implements DateProcessor {

    public Period createPeriod(PeriodsEnum period, String startDate, String endDate) {

        switch (period) {

            case TODAY:
                return setTodayPeriod();

            case YESTERDAY:
                return setYesterdayPeriod();

            case LAST_WEEK:
                return setLastWeekPeriod();

            case LAST_MONTH:
                return setLastMonthPeriod();

            case LAST_YEAR:
                return setLastYearPeriod();

            case CUSTOM:
                return setCustomPeriod(startDate, endDate);

            default:
                throw new CustomException("Wrong request parameters!");
        }

    }

    private Period setCustomPeriod(String startDate, String endDate) {

        return new Period(
                DateTime.parse(startDate).toDate(),
                DateTime.parse(endDate).toDate());
    }

    private Period setLastYearPeriod() {

        DateTime endDate = DateTime.now();

        DateTime startDate = roundDayMin(endDate);
        startDate = startDate.minusYears(1);

        return new Period(startDate.toDate(), endDate.toDate());
    }

    private Period setLastMonthPeriod() {

        DateTime endDate = DateTime.now();

        DateTime startDate = roundDayMin(endDate);
        startDate = startDate.minusMonths(1);

        return new Period(startDate.toDate(), endDate.toDate());
    }

    private Period setLastWeekPeriod() {

        DateTime endDate = DateTime.now();

        DateTime startDate = roundDayMin(endDate);
        startDate = startDate.minusDays(7);

        return new Period(startDate.toDate(), endDate.toDate());
    }

    private Period setYesterdayPeriod() {

        DateTime endDate = new DateTime(new Date());
        endDate = endDate.minusDays(1);
        endDate = roundDayMax(endDate);

        DateTime startDate = roundDayMin(endDate);
        startDate = startDate.minusDays(1);

        return new Period(startDate.toDate(), endDate.toDate());
    }

    private Period setTodayPeriod() {

        DateTime endDate = DateTime.now();
        DateTime startDate = roundDayMin(endDate);

        return new Period(startDate.toDate(), endDate.toDate());
    }

    private DateTime roundDayMin(final DateTime date) {

        return new DateTime(date)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
    }

    private DateTime roundDayMax(final DateTime date) {

        return new DateTime(date)
                .withHourOfDay(23)
                .withMinuteOfHour(59)
                .withSecondOfMinute(59)
                .withMillisOfSecond(999);
    }
}
