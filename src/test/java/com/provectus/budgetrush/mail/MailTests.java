package com.provectus.budgetrush.mail;

import static org.junit.Assert.assertTrue;

import javax.mail.MessagingException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.base.Strings;

@Slf4j
public class MailTests {
	
	private MailSender mailSender;
	private AnnotationConfigApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(MailConfig.class);
		ctx.refresh();

		mailSender = (MailSender) ctx.getBean("mailSender");
	}
	
	@Test
	public void SendTestResetMail() throws MessagingException {

		String text = ResetPasswordMessageBuilder.newInstance()
				.setName("Anonymus").setPassword("test").build();

		assertTrue(!Strings.isNullOrEmpty(text));

		mailSender.sendEmail("baevkir@gmail.com",
				"Test mail", text);

		log.info("---Done---");
	}
} 