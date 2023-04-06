package com.hospitaltask.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.entity.Email;
import com.hospitaltask.entity.Otp;
import com.hospitaltask.repository.OtpRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.OtpService;

import freemarker.template.TemplateException;


@RestController
@RequestMapping("/forget")
public class ForgetController {
    @Autowired
    private OtpService otpService;

    @Autowired
    private OtpRepo otpRepo;
    String emailForget;

/*
    @SuppressWarnings("unused")
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
*/


    @PostMapping("/send")
    public ResponseEntity<?> email(@RequestBody @NotNull Email email) throws MessagingException, TemplateException, IOException {
        /*Boolean otp = otpService.Otp(email.getSubject(), email.getTo());
        if (otp) return CustomResponseHandler.response("Email Send ", HttpStatus.OK, "Okay");
        else */return CustomResponseHandler.response("OTP Not Match", HttpStatus.BAD_REQUEST, "Bad Credentials ");
    }

    @PostMapping("/match")
    public ResponseEntity<?> match(@RequestBody Otp otp) {
       /* Boolean match = otpService.match(otp);
        String lastInsertedValue = otpRepo.getLastInsertedValue1();
        if (match && otp.getOtp() == lastInsertedValue) {

            otpService.passwordUpdate(otp.getPassword(), "durgesh.y@drcsystems.com");
            return CustomResponseHandler.response("Otp Match ", HttpStatus.OK, "Okay");
        } else if (match && otp.getOtp() != lastInsertedValue) {
            return CustomResponseHandler.response("Incorrect Otp", HttpStatus.BAD_REQUEST, "Bad Credentials ");
        }
        else  */return CustomResponseHandler.response("OTP Expired", HttpStatus.FORBIDDEN, "");
    }
}
