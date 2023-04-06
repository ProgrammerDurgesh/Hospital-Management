package com.hospitaltask.service;

import com.hospitaltask.entity.OtpVerify;
import com.hospitaltask.entity.Otp;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface OtpService {
    void save(OtpVerify otpVerify);


    public String generateEmailOTP(String email,int userType);

    int match(String otp);
    String passwordUpdate(String password,String email);
}
