package com.hospitaltask.controller;

import com.hospitaltask.entity.*;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.service.DoctorService;
import com.hospitaltask.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetails myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

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


    @PostMapping("Authenticate")
    ResponseEntity<?>  createAuthenticationToken(@RequestBody Login login) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            System.out.println("Username :      "+login.getEmail());
            System.out.println("password:   "+login.getPassword());


        }
        catch (BadCredentialsException e)
        {
            System.out.println("Bad Credential ");
            e.printStackTrace();
            throw new Exception("incorrect Username Or Password ",e);

        }

        final UserDetails userDetails=myUserDetailsService.loadUserByUsername(login.getEmail());
        final String JWT= jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(JWT));


    }
    @GetMapping("/okay")
    String okay()
    {
        return "this is Okay"
;    }





}
