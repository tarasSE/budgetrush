package com.provectus.budgetrush.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class MailService {

    @Getter
    @Setter
    private String userName;


    @Getter
    @Setter
    private String password;

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



}
