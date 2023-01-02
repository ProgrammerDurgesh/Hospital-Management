package com.hospitaltask.securityconfig;
import com.hospitaltask.entity.Doctor;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hospitaltask.service.MyUserDetails;

import javax.print.Doc;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  {



    @Autowired
    private MyUserDetails myUserDetails ;


    private static final String[] authorizedURL = {"/HM/**","/swagger-**"};
    @Bean
    PasswordEncoder passwordEncoder()
        {
            return  new BCryptPasswordEncoder ( 11 );
        }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(authorizedURL).permitAll()
               // .anyRequest()
//                .authenticated()
//                .and()
               // .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                //.and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               ;
       // http
                //.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
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
    public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetails);

    }
}
