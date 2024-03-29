package com.example.demo.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage smm= preparar(obj);
		sendEmail(smm);
	}
	
	protected SimpleMailMessage preparar(Pedido obj) {
		SimpleMailMessage smm= new SimpleMailMessage();
		smm.setTo(obj.getCliente().getEmail());
		smm.setFrom(sender);
		smm.setSubject("ID do pedido:"+ obj.getId());
		smm.setSentDate(new Date(System.currentTimeMillis()));
		smm.setText(obj.toString());
		return smm;
	}
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context= new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("enviaEmail", context);
	}
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepararMime(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			this.sendOrderConfirmationEmail(obj);
		}
		
	}
	protected MimeMessage prepararMime(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mmh= new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("ID do pedido:"+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}
	
}
