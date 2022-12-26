package com.hospitaltask.service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Login;
import com.hospitaltask.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceImp implements UserDetailsService {

    @Autowired
    DoctorRepo doctorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor =doctorRepo.findByEmail(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (doctor == null) {
            throw new UsernameNotFoundException("User not authorized.");
        } else {

            grantedAuthorities.add(new SimpleGrantedAuthority(doctor.getEmail()));

            return new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(), grantedAuthorities);
        }
    }
}
