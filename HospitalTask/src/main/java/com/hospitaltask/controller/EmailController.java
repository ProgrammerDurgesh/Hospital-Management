package com.hospitaltask.controller;

import com.hospitaltask.entity.Email;
import com.hospitaltask.response.CustomResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@RestController
@RequestMapping("/email")
public class EmailController {

    @PostMapping("/send")
    public ResponseEntity<?> email(@RequestBody Email email) {
        String message =email.getMessage();
        String subject = email.getSubject();
        String to = email.getTo();
        String from=email.getFROM_EMAIL();

        //sendEmail(message, subject, to, from);
        sendAttached(message, subject, to, from);

        return CustomResponseHandler.response("Email Send ", HttpStatus.OK, "Okay");
    }




    //this is responsible to send email
    private static void sendEmail(String message, String subject, String to, String from) {

        //variable for gmail host
        String host = "smtp.gmail.com";
        //get system properties
        Properties properties = System.getProperties();
        System.out.println("this is properties of systems :     " + properties);
        //set important information in properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //to get the session object
        Session instance = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("testdemo000011@gmail.com", "gqqttubrbjsynvtc");

            }
        });

        instance.setDebug(true);
        //step 2 compose the message[text,attachment,multimedia];
        MimeMessage mimeMessage = new MimeMessage(instance);
        try {
            //set message from
            mimeMessage.setFrom(from);

            //add recipients
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //adding subject to message
            mimeMessage.setSubject(subject);
            //adding text to message
            mimeMessage.setText(message);
            //step 3 : send the message from transport class
            Transport.send(mimeMessage);
            System.out.println("Message Send success.......................");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //this is responsible to `send message with attachment
    private void sendAttached(String message, String subject, String to, String from) {

        //variable for gmail host
        String host = "smtp.gmail.com";
        //get system properties
        Properties properties = System.getProperties();
        System.out.println("this is properties of systems :     " + properties);
        //set important information in properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //to get the session object
        Session instance = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "gqqttubrbjsynvtc");

            }
        });

        instance.setDebug(true);
        //step 2 compose the message[text,attachment,multimedia];
        MimeMessage mimeMessage = new MimeMessage(instance);
        try {
            //set message from
            mimeMessage.setFrom(from);

            //add recipients
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //adding subject to message
            mimeMessage.setSubject(subject);
            //adding attachment to message
            String path = "/home/durgesh/Desktop/sigin.html";
            mimeMessage.setText(message);
            MimeBodyPart text = new MimeBodyPart();
            MimeBodyPart file = new MimeBodyPart();
            MimeMultipart mimeMultipart = new MimeMultipart();
            try {
                text.setText(message);
                File file1 = new File(path);
                file.attachFile(file1);
                mimeMultipart.addBodyPart(text);
                mimeMultipart.addBodyPart(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
            mimeMessage.setContent(mimeMultipart);


            //step 3 : send the message from transport class
            Transport.send(mimeMessage);
            System.out.println("Message Send success.......................");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}