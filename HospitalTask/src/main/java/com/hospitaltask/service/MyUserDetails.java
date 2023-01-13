package com.hospitaltask.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @SuppressWarnings("unused")
    private Doctor doctor;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Doctor doctor=doctorRepo.findByEmail(username);
    	Patient patient =entityRepo.findByEmail(username);
      
        if(doctor !=null)
        {
        	return new User(doctor.getEmail(), doctor.getPassword(), getAuthority(doctor,patient));
        }
        else if(patient != null)
        {
            return new User(patient.getEmail(), patient.getPassword(), getAuthority(doctor,patient));
        }
        else
        {
            throw new  UserNotFoundException("User not Found Exception");
        }
    }
    private Set<SimpleGrantedAuthority> getAuthority(Doctor doctor,Patient patient) {
    	Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
    	if(doctor !=null) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(doctor.getRoles().getRoleName());
        authorities.add(simpleGrantedAuthority);
    	}
    	else
    	{
    		 SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(patient.getRoles().getRoleName());
    	        authorities.add(simpleGrantedAuthority);
    	}
        return authorities;
    }
}
