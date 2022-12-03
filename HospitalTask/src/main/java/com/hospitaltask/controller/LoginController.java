package com.hospitaltask.controller;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Login;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class LoginController {


    @Autowired
    private DoctorService doctorService;
    @Autowired
    private
    PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public String login( @RequestBody Login login )
        {
            System.out.println ("Email :"+login.getEmail ()+"  "+"Password   :"+login.getPassword ());
            String dbPassword=doctorService.getPasswordByEmail(login.getEmail ());
             passwordEncoder = new BCryptPasswordEncoder();
               if(passwordEncoder.matches(login.getPassword(),dbPassword)) {
                   System.out.println("Password Match");
                   return "Credential okay ";
               }
               else {
                   System.out.println("Password incorrect");
                   return "Password Incorrect";
               }

        }



}
