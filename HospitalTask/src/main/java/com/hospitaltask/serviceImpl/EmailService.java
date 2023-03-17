package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.UserInfo;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.NotNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailService {

    final Configuration configuration;
    final JavaMailSender javaMailSender;
    Map<String, Object> model = new HashMap<>();

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(@NotNull UserInfo user) throws MessagingException, IOException, TemplateException {

       /* MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessage Message = javaMailSender.createMimeMessage();

        try {
            System.out.println(user.getEmail());
            System.out.println(user.getEmail());

            MimeMessageHelper helper = new MimeMessageHelper(Message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            String conformationToken = UUID.randomUUID().toString();

            String url = "127.0.0.1:8000/patient/verify/" + user.getEmail() + "/" + conformationToken;
            Template template = configuration.getTemplate("varificationEmaiBuuton.html");
            model.put("abc", url);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText("OTP : " + url);
            helper.setText(html, true);
            mimeMessage.setSubject("Account Activation !! . ");

            String emailContent = user.getEmail();
            helper.setTo(emailContent);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("Account Verification !!");
            helper.setTo(user.getEmail());
            String conformationToken = UUID.randomUUID().toString();
            String url = "127.0.0.1:8000/patient/verify/" + user.getEmail() + "/" + conformationToken;
            helper.setText("OTP : " + url);
            String emailContent = getEmailContent(user);
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getEmailContent(UserInfo user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        try {

            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            configuration.getTemplate("varificationEmaiBuuton.html").process(model, stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.getBuffer().toString();
    }

}
