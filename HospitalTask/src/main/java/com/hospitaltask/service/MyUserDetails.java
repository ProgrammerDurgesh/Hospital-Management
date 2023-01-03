package com.hospitaltask.service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Login;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetails implements UserDetailsService {
    private Doctor doctor;

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private Login login;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor=doctorRepo.findByEmail(username);
        if(username.equals(doctor.getUsername()))
        {
            return  new User(doctor.getUsername(),doctor.getPassword(),new ArrayList<>());
        }
        else
        {
            throw new  UserNotFoundException("User not Found Exception");
        }

    }
}
