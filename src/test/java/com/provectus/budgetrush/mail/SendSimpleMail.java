package com.provectus.budgetrush.mail;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SendSimpleMail {
    @Test
    public void MailTest() throws MessagingException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MailConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("budgetrush@gmail.com");
        mailMsg.setTo("budgetrush@gmail.com"); //todo testing without sending message
        mailMsg.setSubject("Test mail");
        mailMsg.setText("Hello World!");
        mailSender.send(mimeMessage);
        System.out.println("---Done---");
    }
} 