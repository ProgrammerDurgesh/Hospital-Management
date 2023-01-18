package com.hospitaltask.controller;

import com.hospitaltask.auth.AuthRequest;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.MyUserDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dur")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetails myUserDetailsService;
    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private SuperAdminRepo superAdminRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @NotNull AuthRequest authRequest) throws Exception {

        System.out.println("Request Data    :    " + authRequest.toString());
        Authentication authenticate;
        String getPasswordByEmailFromDB = null;
        Doctor doctor = doctorRepo.getDoctorByEmail(authRequest.getUserName());
        if (doctor != null) getPasswordByEmailFromDB = doctor.getPassword();
        else {
            Patient patient = entityRepo.findByEmail(authRequest.getUserName());
            if (patient == null) {
                SuperAdmin superAdmin = superAdminRepo.findByEmail(authRequest.getUserName());
                if (superAdmin == null) {
                    return CustomResponseHandler.response("Email incorrect", HttpStatus.BAD_REQUEST, authRequest.getUserName());
                }
                getPasswordByEmailFromDB = superAdmin.getPassword();
            } else {
                getPasswordByEmailFromDB = patient.getPassword();
            }
        }
        try {
           // boolean matches = passwordEncoder.matches(authRequest.getPassword(), getPasswordByEmailFromDB);
            authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        } catch (Exception e) {
            return CustomResponseHandler.response("Password incorrect", HttpStatus.BAD_REQUEST, authRequest.getPassword());
        }
        if (authenticate.isAuthenticated()) {
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
            String token = this.jwtUtil.generateToken(userDetails);
            return CustomResponseHandler.response("Token Create successfully ", HttpStatus.ACCEPTED, token);
        } else {
            return CustomResponseHandler.response("Credential Not Found", HttpStatus.NOT_FOUND, authRequest.getUserName() + authRequest.getPassword());
        }
    }


}