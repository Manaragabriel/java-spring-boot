package com.example.demo.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService  extends AbstractEmailService{

	@Autowired
	private MailSender sender;
	
	@Autowired JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		//LOG.info("Enviando email");
		//LOG.info(msg.toString());
		sender.send(msg);
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		javaMailSender.send(msg);
	}

	
}
