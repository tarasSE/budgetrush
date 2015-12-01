package com.provectus.budgetrush.dateproc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.provectus.budgetrush.data.Periods.*;

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

        dateProcessorBean.createPeriod(TODAY, null, null);
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

        dateProcessorBean.createPeriod(YESTERDAY, null, null);
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

        dateProcessorBean.createPeriod(LAST_WEEK, null, null);
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

        dateProcessorBean.createPeriod(LAST_MONTH, null, null);
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

        dateProcessorBean.createPeriod(LAST_YEAR, null, null);
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

        dateProcessorBean.createPeriod(CUSTOM, "2015-02-01", "2015-01-01");
        log.info(dateProcessorBean.getStartDate().toDate().toString() + dateProcessorBean.getEndDate().toDate().toString());

    }


}

