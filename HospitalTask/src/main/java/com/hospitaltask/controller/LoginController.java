package com.hospitaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.auth.AuthRequest;
import com.hospitaltask.auth.AuthResponse;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.MyUserDetails;

@RestController
@RequestMapping("/hm")
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
	public ResponseEntity<?> login(@ModelAttribute AuthRequest authRequest) throws Exception {
		/*
		 * try {
		 * 
		 * System.out.println("okay"); String getPasswordByEmail=null;
		 * getPasswordByEmail=this.doctorRepo.getPasswordByEmail(authRequest.getUserName
		 * ()); if(getPasswordByEmail==null) { //
		 * getPasswordByEmail=entityRepo.findByEmail(authRequest.getUserName()); }
		 * System.out.println(authRequest.getUserName());
		 * System.out.println(authRequest.getPassword());
		 * 
		 * Authentication authenticate = this.authenticationManager.authenticate(new
		 * UsernamePasswordAuthenticationToken(authRequest.getUserName() ,
		 * authRequest.getPassword()));
		 * 
		 * boolean matches = passwordEncoder.matches(authRequest.getPassword(),
		 * getPasswordByEmail); if (!matches) throw new
		 * BadCredentialsException("incorrect Username Or Password");
		 * System.out.println(matches);
		 * 
		 * 
		 * } catch (UsernameNotFoundException e) { e.printStackTrace(); throw new
		 * Exception("Bad Credentials"); }catch (BadCredentialsException e) {
		 * e.printStackTrace(); throw new Exception("incorrect Username Or Password ",
		 * e); }
		 */
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());

		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("Jwt Token :        " + token);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	/*
	 * @PostMapping("Authenticate") ResponseEntity<?>
	 * createAuthenticationToken(@RequestBody AuthRequest login) throws Exception {
	 * try { authenticationManager .authenticate(new
	 * UsernamePasswordAuthenticationToken(login.getUserName(),
	 * login.getPassword())); } catch (BadCredentialsException e) {
	 * e.printStackTrace(); throw new Exception("incorrect Username Or Password ",
	 * e); } final UserDetails userDetails =
	 * myUserDetailsService.loadUserByUsername(login.getUserName()); final String
	 * JWT = jwtUtil.generateToken(userDetails); return ResponseEntity.ok(new
	 * LoginResponse(JWT)); }
	 */
}