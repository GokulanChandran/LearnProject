package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;

@Component
public class SampleClass 
{
	
	@PostConstruct
	public void check() throws Exception
	{
		System.out.println("addFeatures");
		final String fromEmail = "gokulanlearn@gmail.com"; //requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "gokulanlearn@gmail.com"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		System.out.println("called");
		//sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
	//}
	
	//public static void sendEmail(Session session, String toEmail, String subject, String body) throws Exception
	//{
		MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

	      msg.setSubject("TLSEmail Testing Subject", "UTF-8");

	      msg.setText("TLSEmail Testing Body", "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
	      Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	}
}
