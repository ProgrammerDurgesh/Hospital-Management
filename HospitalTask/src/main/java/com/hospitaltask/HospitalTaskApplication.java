package com.hospitaltask;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/*import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
*/
@SpringBootApplication
//@SecurityScheme(name = "Durgesh", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
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
