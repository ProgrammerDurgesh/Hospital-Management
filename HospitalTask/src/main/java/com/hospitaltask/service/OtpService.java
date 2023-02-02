package com.hospitaltask.service;

import com.hospitaltask.entity.OtpVerify;

public interface OtpService {
    void save(OtpVerify otpVerify);

    Boolean Otp(String subject, String to, String from);

    public String generateEmailOTP(String email);

    Boolean match(String otp);
}
