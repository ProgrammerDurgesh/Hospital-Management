package com.hospitaltask.securityconfig;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.jwt.JwtAuthenticationFilter;
import com.hospitaltask.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hospitaltask.service.MyUserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.print.Doc;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private MyUserDetails myUserDetails ;


    private static final String[] authorizedURL = {"/login","/swagger-**"};
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(authorizedURL).permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
//    @Bean
//    public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetails);
//
//    }
}
