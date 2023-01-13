package com.hospitaltask.controller;

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

import com.hospitaltask.auth.AuthRequest;
import com.hospitaltask.auth.AuthResponse;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.MyUserDetails;

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

	/*@GetMapping("/home")
	String home()
	{
		return "Home";
	}

	@GetMapping("/login")
		String login() {
			System.out.println("this is login Page ");
			return "login";
		}
	@GetMapping("/create-new")
	String createNewPatient() {
		System.out.println("this is login Page ");
		return "patientCreate";
	}*/

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
		System.out.println(
				"Request Data    :    "+authRequest.toString()
		);
		String getPasswordByEmailFromDB = null;
		Doctor doctor = doctorRepo.getDoctorByEmail(authRequest.getUserName());
		
		if (doctor != null) {
			getPasswordByEmailFromDB = doctor.getPassword();
		} else {
			Patient patient = entityRepo.findByEmail(authRequest.getUserName());
			getPasswordByEmailFromDB = patient.getPassword();
		}
		Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName()
				, authRequest.getPassword()));//boolean matches = passwordEncoder.matches(authRequest.getPassword(), getPasswordByEmailFromDB);


		String response = null;
		//System.out.println(matches);
		if (authenticate.isAuthenticated()) {
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
			String token = this.jwtUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthResponse(token));
		} else {
			response = "User Not Found  ";
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}