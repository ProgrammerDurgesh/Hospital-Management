package com.hospitaltask.controller;

import com.hospitaltask.auth.AuthRequest;
import com.hospitaltask.auth.AuthResponse;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.repository.RoleRepo;
import com.hospitaltask.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
	@Autowired
	private RoleRepo roleRepo;

	@PostMapping("/login")
	public ResponseEntity<?> login(@ModelAttribute AuthRequest authRequest) throws Exception {
		String getPasswordByEmailFromDB=null;
		Doctor doctor=doctorRepo.getDoctorByEmail(authRequest.getUserName());
		if(doctor!=null)
		{
			getPasswordByEmailFromDB=doctor.getPassword();
		}
		else {
			Patient patient=entityRepo.findByEmail(authRequest.getUserName());
			getPasswordByEmailFromDB=patient.getPassword();
		}
		boolean matches = passwordEncoder.matches(authRequest.getPassword(), getPasswordByEmailFromDB);
		String response = null;
			System.out.println(matches);
			if (matches==true) {
				UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(authRequest.getUserName());
				String token = this.jwtUtil.generateToken(userDetails);
				return ResponseEntity.ok(new AuthResponse(token));
			}
			else
			{
				response = "User Not Found  ";
				return new  ResponseEntity(response, HttpStatus.NOT_FOUND);
			}
}



}