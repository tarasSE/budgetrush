package com.provectus.budgetrush.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSender extends JavaMailSenderImpl {

	public void sendEmail(String recipient, String subject, String text) {
		sendEmail(getUsername(), recipient, subject, text);
	}
	
	public void sendEmail(String sender, String recipient, String subject, String text) {
		MimeMessage mimeMessage = createMimeMessage();

		MimeMessageHelper mailMsg;
		try {
			mailMsg = new MimeMessageHelper(mimeMessage, true);
			mailMsg.setFrom(sender);
			mailMsg.setTo(recipient);
			mailMsg.setSubject(subject);
			mailMsg.setText(text, true);
		} catch (MessagingException exception) {
			String message ="Can`t send email to "+recipient;
			log.warn(message+" "+exception);
			throw new RuntimeException(message);
		}

		send(mimeMessage);
	}
}
