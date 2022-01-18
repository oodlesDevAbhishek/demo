package io.oodles.demo.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public int optGen() {
		Random random =new Random();
		return random.nextInt(999999);
	}
	
	public boolean sendEmail(String senderId , int token) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("abhishek.gupta1@oodles.io");
		message.setTo(senderId);
		message.setSubject("Reset Password");
		message.setText("You Reset Token Code is : "+token);
		
		javaMailSender.send(message);
		return true;
	}
	public boolean sendEmail(String senderId) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("abhishek.gupta1@oodles.io");
		message.setTo(senderId);
		message.setSubject("Update Password");
		message.setText("password successfully changed...!");
		
		javaMailSender.send(message);
		return true;
	}
	
	
	
	
	
	
	   
}
