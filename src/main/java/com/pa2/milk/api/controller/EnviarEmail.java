package com.pa2.milk.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmail {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("adryel.juliao1@gmail.com");

		msg.setSubject("Cadastro efetuado com sucesso.");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}
}
