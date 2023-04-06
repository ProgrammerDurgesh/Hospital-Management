package com.hospitaltask.serviceImpl;
/*
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.storage.StorageScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;*/

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.hospitaltask.entity.Otp;
import com.hospitaltask.repository.OtpRepo;
import com.hospitaltask.service.OtpService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
@SuppressWarnings("unused")
public class EmailService {

    private static final String NUMBERS = "0123456789";
    private static Random random = new Random();



    final Configuration configuration;
    final JavaMailSender javaMailSender;
    Map<String, Object> model = new HashMap<>();
    @Autowired
    private OtpRepo otpRepo;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public Boolean sendEmail(@NotNull String email ,String subject,String text) throws MessagingException, IOException, TemplateException {
        boolean valid = false;

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("Account Verification !!");
            String conformationToken = UUID.randomUUID().toString();
            String url = "8000/patient/verify/" + email + "/" + conformationToken;
            helper.setTo(email);
            String emailContent = getEmailContent(email, url);
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    String getEmailContent(String email, String url) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        try {

            Map<String, Object> model = new HashMap<>();
            model.put("user", email);
            model.put("url", url);
            configuration.getTemplate("free.ftl").process(model, stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.getBuffer().toString();
    }


    public  void sendWithOutHtmlPage(@NotNull String email ,String subject,String text)
    {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(subject);
            helper.setTo(email);
            helper.setText(text);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

