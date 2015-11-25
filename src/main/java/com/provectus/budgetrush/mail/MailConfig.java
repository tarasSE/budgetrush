package com.provectus.budgetrush.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableTransactionManagement
public class MailConfig {
    @Bean
    public MailSender mailSender() {

        log.info("Creating mail sender.");
        return new MailService().getMailSender();
    }

//    private String getUserName() {
//
//        String userName = null;
//
//        try {
//            Properties properties = new Properties();
//            InputStream stream = Resources.getResource("app.properties").openStream();
//            properties.load(stream);
//            stream.close();
//
//            userName = properties.getProperty("mail-notify.username");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("Oops, It seems that something wrong with getting app.properties!");
//        }
//        return userName;
//    }
//
//    private String getPassword() {
//
//        String password = null;
//
//        try {
//            Properties properties = new Properties();
//            InputStream stream = Resources.getResource("app.properties").openStream();
//            properties.load(stream);
//            stream.close();
//
//            password = properties.getProperty("mail-notify.password");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("Oops, It seems that something wrong with getting app.properties!");
//        }
//        return password;
//    }

} 