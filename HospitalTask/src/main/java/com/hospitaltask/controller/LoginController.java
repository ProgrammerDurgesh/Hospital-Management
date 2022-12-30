package com.hospitaltask.controller;

import com.hospitaltask.auth.AuthResponse;
import com.hospitaltask.entity.*;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.service.DoctorService;
import com.hospitaltask.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetails myUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody Login login) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
			System.out.println("authentication   :    "+authentication);
			Doctor user = (Doctor) authentication.getPrincipal();
			String accesToken = jwtUtil.generateToken(user);
			System.out.println("Generate JwtToke :   " + accesToken);
			AuthResponse response = new AuthResponse(user.getEmail(), user.getPassword(), accesToken);
			return ResponseEntity.ok().body(response);
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.toString());
		}
	}

	@PostMapping("Authenticate")
	ResponseEntity<?> createAuthenticationToken(@RequestBody Login login) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("incorrect Username Or Password ", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(login.getEmail());
		final String JWT = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(JWT));
	}
}
