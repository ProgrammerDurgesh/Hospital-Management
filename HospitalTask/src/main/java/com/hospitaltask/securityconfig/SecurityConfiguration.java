package com.hospitaltask.securityconfig;

import com.hospitaltask.jwt.JwtAuthenticationFilter;
import com.hospitaltask.repository.RoleRepo;
import com.hospitaltask.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private MyUserDetails myUserDetails;
	private static final String[] authorizedURL = { "/hm/home" ,"/swagger-ui**"};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new MyUserDetails();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(authorizedURL)
		.permitAll()
		.antMatchers("/doctor/**").hasAnyAuthority("ROLE_DOCTOR")
		.antMatchers("/patient/**").hasAnyAuthority("ROLE_PATIENT")
		.and()
		.formLogin()
		.and()
		.logout();
		/*
		 * .cors() .disable() .csrf() .disable()
		 * .authorizeRequests().antMatchers("/**").permitAll()
		 * .antMatchers("/doctor/*").hasRole("DOCTOR")
		 * .antMatchers("/patient/**").hasRole("PATIENT")
		 * 
		 * .and()
		 * 
		 * .antMatchers()
		 * .authenticated().antMatchers(HttpMethod.GET,"HM/doctor").hasRole("ADMIN")
		 * .antMatchers().authenticated().antMatchers(HttpMethod.GET,"/pm/patient").
		 * hasRole("PATIENT") //.and() .sessionManagement()
		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 * http.addFilterBefore(jwtAuthenticationFilter,
		 * UsernamePasswordAuthenticationFilter.class);
		 */
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
//    @Bean
//    public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//
//    }
}
