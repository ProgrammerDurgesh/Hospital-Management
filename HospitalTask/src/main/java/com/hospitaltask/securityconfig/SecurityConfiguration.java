package com.hospitaltask.securityconfig;

import com.hospitaltask.jwt.JwtRequestFilter;
import com.hospitaltask.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration  {

    @Autowired
    private MyUserDetails myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    public static final String [] WHITE_LIST_URLS=
            {
                    "/home",
                    "/AddDoctor",
                    "/doctor",
                    "/doctor/findById/{id}" ,
                    "doctor/{id}",
                    "doctor/doctorName/{doctorName}",
                    "/AddClinic",
                    "clinic/name/{name}",
                    "/clinic",
                    "/clinic/{id}",
                    "/clinic/clinicName/{clinicName}",
                    "/AddPatient",
                    "/patient/{id}",
                    "/patient",
                    "/patient/email/{email}",
                    "/patient/name/{name}",
                    "/patient/doctor/{doctor}",
                    "patient/patient/{Id}"
            };

    @Bean
    PasswordEncoder passwordEncoder()
        {
            return  new BCryptPasswordEncoder ( 11 );
        }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll()
               // .antMatchers("/HM/**").authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("userPass")
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password("adminPass")
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
