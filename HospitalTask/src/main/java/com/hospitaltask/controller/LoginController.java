package com.hospitaltask.controller;

import com.hospitaltask.auth.AuthRequest;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.MyUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dur")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetails myUserDetailsService;
    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        String str=null;
        System.out.println(str.length());
        System.out.println("Request Data    :    " + authRequest.toString());
        Authentication authenticate;
        String getPasswordByEmailFromDB = null;
        Doctor doctor = doctorRepo.getDoctorByEmail(authRequest.getUserName());
        if (doctor != null) getPasswordByEmailFromDB = doctor.getPassword();
        else {
            Patient patient = entityRepo.findByEmail(authRequest.getUserName());
            if (patient == null)
                return CustomResponseHandler.response("Email incorrect", HttpStatus.BAD_REQUEST, authRequest.getUserName());
            getPasswordByEmailFromDB = patient.getPassword();
        }
        try {
            authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));//boolean matches = passwordEncoder.matches(authRequest.getPassword(), getPasswordByEmailFromDB);
        } catch (Exception e) {
            return CustomResponseHandler.response("Password incorrect", HttpStatus.BAD_REQUEST, authRequest.getPassword());
        }
        String response = null;
        if (authenticate.isAuthenticated()) {
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
            String token = this.jwtUtil.generateToken(userDetails);
            return CustomResponseHandler.response("Token Create successfully ", HttpStatus.OK, token);
        } else {
            response = "User Not Found  ";
            return CustomResponseHandler.response("Credential Not Found", HttpStatus.NOT_FOUND, authRequest.getUserName() + authRequest.getPassword());
        }
    }


}