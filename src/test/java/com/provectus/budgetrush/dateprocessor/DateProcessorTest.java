package com.provectus.budgetrush.dateprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.provectus.budgetrush.data.PeriodsEnum.*;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DateProcessorBean.class})
@WebAppConfiguration
public class DateProcessorTest {

    @Autowired
    private DateProcessorBean dateProcessorBean;

    @Test
    public void DateTypeTest() {

        Period period;

        period = dateProcessorBean.createPeriod(TODAY, null, null);
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

        period = dateProcessorBean.createPeriod(YESTERDAY, null, null);
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

        period = dateProcessorBean.createPeriod(LAST_WEEK, null, null);
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

        period = dateProcessorBean.createPeriod(LAST_MONTH, null, null);
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

        period = dateProcessorBean.createPeriod(LAST_YEAR, null, null);
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

        period = dateProcessorBean.createPeriod(CUSTOM, "2015-02-01", "2015-01-01");
        log.info(period.getStartDate().toString() + " " + period.getEndDate().toString());

    }


}

