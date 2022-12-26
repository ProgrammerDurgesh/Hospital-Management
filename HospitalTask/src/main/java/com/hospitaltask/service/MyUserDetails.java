package com.hospitaltask.service;

import com.hospitaltask.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private Login login;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName=login.getEmail();
        String password=login.getPassword();

        System.out.println("abcd"+userName);
        System.out.println("abcd"+password);
        return new User(userName,password,new ArrayList<>());
    }
}
