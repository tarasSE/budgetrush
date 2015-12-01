package com.provectus.budgetrush.mail;

import com.google.common.base.Strings;
import com.provectus.budgetrush.utils.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.mail.MessagingException;

import static org.junit.Assert.assertTrue;

@Slf4j
public class MailTests {
	
	private MailSender mailSender;
	private AnnotationConfigApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();

		mailSender = (MailSender) ctx.getBean("mailSender");
	}
	
	@Test
	public void SendTestResetMail() throws MessagingException {

		String text = ResetPasswordMessageBuilder.newInstance()
				.setName("Anonymus").setPassword("test").build();

		assertTrue(!Strings.isNullOrEmpty(text));

		mailSender.sendEmail(mailSender.getUsername(),
				"Test mail", text);

		log.info("---Done---");
	}
} 