package com.hospitaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalTaskApplication.class, args);
        System.out.println("This is Hospital Management");
    }

}
