package com.hospitaltask.service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetails implements UserDetailsService {
    private Doctor doctor;

    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor=doctorRepo.findByEmail(username);
        Patient patient=this.entityRepo.findByEmail(username);
        String email=null,password=null;
        if(doctor !=null)
        {
            email=doctor.getEmail();
            password=doctor.getPassword();
        }
        else {
            email = patient.getEmail();
            password=patient.getPassword();
        }
        if(username.equals(email))
        {
            return  new User(email, password,new ArrayList<>());
        }
        else
        {
            throw new  UserNotFoundException("User not Found Exception");
        }

    }
}
