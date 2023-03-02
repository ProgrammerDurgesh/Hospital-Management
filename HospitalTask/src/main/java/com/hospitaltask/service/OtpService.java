package com.hospitaltask.service;

import com.hospitaltask.entity.OtpVerify;
import com.hospitaltask.entity.Otp;
public interface OtpService {
    void save(OtpVerify otpVerify);

    Boolean Otp(String subject, String to, String from);

    public String generateEmailOTP(String email);

    Boolean match(Otp otp);
    String passwordUpdate(String password,String email);
}
