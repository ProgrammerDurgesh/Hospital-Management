package com.hospitaltask.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration  {


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
    SecurityFilterChain filterChain( HttpSecurity http ) throws Exception
        {
            http
                    .cors ()
                    .and ()
                    .csrf ()
                    .disable ()
                    .authorizeHttpRequests ()
                    .antMatchers ( WHITE_LIST_URLS )
                    .permitAll ();
            return http.build ();
        }



}
