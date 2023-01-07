package com.hospitaltask.securityconfig;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PatientEntityRepo entityRepo;
    private Doctor doctor;
    private Patient patient;
    String userPassword=null,userName=null,roleNameByRoleId;
    public CustomUserDetails(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;

        if(doctor !=null)
        {
            userName=doctor.getEmail();
            userPassword=doctor.getPassword();
            roleNameByRoleId =doctor.getRoles().getRoleName();
        }
        if (patient!=null)
        {
            userName=patient.getEmail();
            userPassword=patient.getPassword();
            roleNameByRoleId =patient.getRoles().getRoleName();
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(roleNameByRoleId);
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleNameByRoleId);
        return List.of(simpleGrantedAuthority);
    }
    @Override
    public String getPassword() {
        return userPassword;
    }
    @Override
    public String getUsername() {
        return userName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
