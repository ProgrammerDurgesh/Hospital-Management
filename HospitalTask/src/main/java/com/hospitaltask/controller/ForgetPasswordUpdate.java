package com.hospitaltask.controller;

import com.hospitaltask.entity.Otp;
import com.hospitaltask.entity.UpdatePasswordByLink;
import com.hospitaltask.repository.UpdatePasswordByLinkRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.OtpService;
import com.hospitaltask.serviceImpl.EmailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgetUpdate")
public class ForgetPasswordUpdate {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UpdatePasswordByLinkRepo updatePasswordByLinkRepo;
    String tokenData=null,emailData=null;

    @Autowired
    private OtpService otpService;











    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestBody @NotNull UpdatePasswordByLink email)
    {
        String otp= otpService.generateEmailOTP(email.getEmail(),1) ;
        String message = "One Time Password :  " + otp + "\n" + "This Otp Expire with in 5 mint ";
        emailService.sendWithOutHtmlPage(email.getEmail(),email.getSubject(),message);
        return CustomResponseHandler.response("Ok", HttpStatus.OK,"");
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<?> match(String otp)
    {
        int match = otpService.match(otp);
        if(match==0)
        {
            return CustomResponseHandler.response("Otp Expired ",HttpStatus.NOT_FOUND,"");
        }
        else if(match==1)
        {
            return CustomResponseHandler.response("Otp Expired ",HttpStatus.NOT_FOUND,"");
        }
        else
        {
            return CustomResponseHandler.response("Done !",HttpStatus.OK,"");
        }

    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(String email,String password)
    {
        String s=otpService.passwordUpdate(password,email);
        if(s.isEmpty())
        {
            return CustomResponseHandler.response("Password incorrect ",HttpStatus.BAD_REQUEST,"");
        }
        return CustomResponseHandler.response("Password Updated !!!!",HttpStatus.ACCEPTED,"");
    }









    @PostMapping("/sendLink")
    public ResponseEntity<?> forgetPassword(@RequestBody @NotNull UpdatePasswordByLink email)
    {
        //mailService.updatePasswordByLink(email.getEmail());
        return CustomResponseHandler.response("Ok", HttpStatus.OK,"");
    }
    @GetMapping("/verify/{url}/{token}")
    public String linkVerify(@PathVariable String url,@PathVariable String token)
    {
        token=url;
        emailData=token;
        return "CreateNewPassword";
    }

    @PostMapping("/verifyDone")
    public String verifyDone(  @PathVariable String email,@PathVariable String url)
    {

        UpdatePasswordByLink byEmail = updatePasswordByLinkRepo.findByEmail(email);
        /*if(url==byEmail.getLink()){
            System.out.println("Account Verified !!");
        }*/
        return "Home";
    }

}
