package com.hospitaltask.service;


import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.repository.SuperAdminRepo;

@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @SuppressWarnings("unused")
    private Doctor doctor;

    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username);
        Doctor doctor = doctorRepo.findByEmail(username);
        Patient patient = entityRepo.findByEmail(username);
        SuperAdmin superAdmin = superAdminRepo.findByEmail(username);
        if (doctor != null)
            return new User(doctor.getEmail(), doctor.getPassword(), getAuthority(doctor, patient, superAdmin));
        else if (patient != null)
            return new User(patient.getEmail(), patient.getPassword(), getAuthority(doctor, patient, superAdmin));
        else if (superAdmin != null)
            return new User(superAdmin.getEmail(), superAdmin.getPassword(), getAuthority(doctor, patient, superAdmin));
        else throw new UserNotFoundException("User not Found Exception");

    }

    private @NotNull Set<SimpleGrantedAuthority> getAuthority(Doctor doctor, Patient patient, SuperAdmin superAdmin) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        if (doctor != null) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(doctor.getRoles().getRoleName());
            authorities.add(simpleGrantedAuthority);
        } else if (patient != null) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(patient.getRoles().getRoleName());
            authorities.add(simpleGrantedAuthority);
        } else {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(superAdmin.getRoles().getRoleName());
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }
}
