package com.hospitaltask.securityconfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
	String userPassword=null,userName=null,roleNameByRoleId;
    public CustomUserDetails(Doctor doctor, Patient patient) {
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
