package com.hospitaltask.controller;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Login;
import com.hospitaltask.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {

    @Autowired
    private Doctor doctor;
    @Autowired
    private
    DoctorRepo doctorRepo;
    @Autowired
    private
    PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public String login( @RequestBody Login login )
        {
            System.out.println ("Email :"+login.getEmail ()+"  "+"Password   :"+login.getPassword ()+"   "+"ConfirmPassword :   "+ login.getConfirmPassword ());

            String pass=passwordEncoder.encode ( login.getPassword () );
            System.out.println (pass );

           if(login.getPassword ().equals (  login.getConfirmPassword ()))
           {

               return "Credential okay ";
           }
           else
           {
               return "Credential Not Match";
           }
        }



}
