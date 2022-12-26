package com.hospitaltask;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HospitalTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalTaskApplication.class, args);
        System.out.println("This is Hospital Management");
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
