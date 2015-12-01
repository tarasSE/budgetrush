package com.provectus.budgetrush.utils;

import com.provectus.budgetrush.dateprocessor.DateProcessorBean;
import com.provectus.budgetrush.mail.MailSender;
import com.provectus.budgetrush.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public MailSender mailSender() {

        log.info("Creating mail sender.");
        MailService mailService = new MailService();
        mailService.setUserName(env.getProperty("mail-notify.userName"));
        mailService.setPassword(env.getProperty("mail-notify.password"));

        return mailService.getMailSender();
    }


    @Bean
    public DateProcessorBean dateProcessor(){

        return new DateProcessorBean();
    }

}
