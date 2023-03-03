package com.hospitaltask.serviceImpl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;
@Component
public class SendEmailTemplate {
	public void sendAttached(String message, String subject, String to) {
		try {
			final String from="testdemo000011@gmail.com";
			// variable for gmail host
			String host = "smtp.gmail.com";
			// get system properties
			Properties properties = System.getProperties();
			System.out.println("this is properties of systems :     " + properties);
			/*
			 * set important information in properties object host set
			 */
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");
			// to get the session object & Authenticate sender I'd & Passcode
			Session instance = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, "gqqttubrbjsynvtc");

				}
			});
			instance.setDebug(true);
			// step 2 compose the message[text,attachment,multimedia];
			MimeMessage mimeMessage = new MimeMessage(instance);
			try {
				// set message from
				mimeMessage.setFrom(from);
				// add recipients
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				// adding subject to message
				mimeMessage.setSubject(subject);
				mimeMessage.setText(message);
				MimeBodyPart text = new MimeBodyPart();
				//MimeBodyPart file = new MimeBodyPart();
				MimeMultipart mimeMultipart = new MimeMultipart();
				try {
					text.setText(message);
					//File file1 = new File(path);
					//file.attachFile(file1);
					mimeMultipart.addBodyPart(text);
					//mimeMultipart.addBodyPart(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mimeMessage.setContent(mimeMultipart);
				// step 3 : send the message from transport class
				Transport.send(mimeMessage);
				System.out.println("Message Send success.......................");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
