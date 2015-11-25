package com.provectus.budgetrush.mail;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MailService {

    public MailSender getMailSender() {
        MailSender mailSender = mailSenderConfig();

        return setMailSenderProperties(mailSender);
    }

    private MailSender setMailSenderProperties(MailSender mailSender) {

        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.debug", "true");

        return mailSender;
    }

    private MailSender mailSenderConfig() {

        MailSender mailSender = new MailSender();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(getUserName());
        mailSender.setPassword(getPassword());

        return mailSender;
    }

    private String getUserName() {

        String userName = null;

        try {
            Properties properties = new Properties();
            InputStream stream = Resources.getResource("app.properties").openStream();
            properties.load(stream);
            stream.close();

            userName = properties.getProperty("mail-notify.username");

        } catch (IOException e) {
            e.printStackTrace();
            log.info("Oops, It seems that something wrong with getting app.properties!");
        }
        return userName;
    }

    private String getPassword() {

        String password = null;

        try {
            Properties properties = new Properties();
            InputStream stream = Resources.getResource("app.properties").openStream();
            properties.load(stream);
            stream.close();

            password = properties.getProperty("mail-notify.password");

        } catch (IOException e) {
            e.printStackTrace();
            log.info("Oops, It seems that something wrong with getting app.properties!");
        }
        return password;
    }

}
