package com.hospitaltask;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    /*@Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private  RoleService roleService;
    @Autowired
    private RoleRepo roleRepo;



    private void superuserSave()
    {
        List<SuperAdmin> superAdmin=superAdminService.findAll();
        if(superAdmin.size()<1)
        {
            SuperAdmin superAdmin1=new SuperAdmin();
            superAdmin1.setId(1);
            superAdmin1.setEmail("Admin@gmail.com");
            superAdmin1.setPassword("Admin");
            superAdmin1.setUserName("Admin");
            List<Roles> roles= roleService.getAllRoles();
            if(roles.size()<1)
            {
                Roles roles1=new Roles();
                roles1.setId(1);
                roles1.setRoleName("ROLE_ADMIN");
                roles1.setRoleDescription("Admin Control");
            }
            superAdmin1.setRoles(roleRepo.getRoleById(1L));
            //superAdminService.save(superAdmin1)
        }
    }*/
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
