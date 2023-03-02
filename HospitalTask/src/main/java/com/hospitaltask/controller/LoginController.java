package com.hospitaltask.controller;

import com.hospitaltask.auth.*;
import com.hospitaltask.entity.*;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.*;
import com.hospitaltask.response.*;
import com.hospitaltask.service.*;
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
import org.springframework.web.bind.annotation.*;

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
		Authentication authenticate;
		String getPasswordByEmailFromDB = null;
		Doctor doctor = doctorRepo.getDoctorByEmail(authRequest.getUserName());
		if (doctor != null && doctor.isFlag()) {
			getPasswordByEmailFromDB = doctor.getPassword();
		} else {
			Patient patient = entityRepo.findByEmail(authRequest.getUserName());
			if (patient != null && patient.isFlag()) {
				getPasswordByEmailFromDB = patient.getPassword();
			} else {
				SuperAdmin superAdmin = superAdminRepo.findByEmail(authRequest.getUserName());
				if (superAdmin != null && superAdmin.isFlag())
					getPasswordByEmailFromDB = superAdmin.getPassword();
				else {
					return CustomResponseHandler.response("Email incorrect", HttpStatus.BAD_REQUEST,
							authRequest.getUserName());
				}
			}
		}

		try {
			// boolean matches = passwordEncoder.matches(authRequest.getPassword(),
			// getPasswordByEmailFromDB);
			authenticate = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (Exception e) {
			return CustomResponseHandler.response("Password incorrect", HttpStatus.BAD_REQUEST,
					authRequest.getPassword());
		}
		if (authenticate.isAuthenticated()) {
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
			String token = this.jwtUtil.generateToken(userDetails);
			return CustomResponseHandler.response("Login successfully ", HttpStatus.ACCEPTED, token);
		} else {
			return CustomResponseHandler.response("Credential Not Found", HttpStatus.NOT_FOUND,
					authRequest.getUserName() + authRequest.getPassword());
		}
	}

}