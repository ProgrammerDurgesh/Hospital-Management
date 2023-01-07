package com.hospitaltask.service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.securityconfig.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    private Doctor doctor;
    private Patient patient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = null, password = null;
        try {
        doctor = doctorRepo.findByEmail(username);
        CustomUserDetails userDetails = new CustomUserDetails(doctor, patient);
        if (doctor != null) {
            email = doctor.getEmail();
            password = doctor.getPassword();
        } else {
            patient = this.entityRepo.findByEmail(username);
            email = patient.getEmail();
            password = patient.getPassword();
        }
        if (username.equals(null)) {
            throw new UserNotFoundException("User not Found Exception");
        }

            return userDetails;
        }
    catch (Exception e)
    {
        e.printStackTrace();
        return null;
    }
    }
}
