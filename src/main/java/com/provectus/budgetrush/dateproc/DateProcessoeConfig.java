package com.provectus.budgetrush.dateproc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateProcessoeConfig {

    @Bean
    public DateProcessorBean dateProcessor(){

        return new DateProcessorBean();
    }

}
