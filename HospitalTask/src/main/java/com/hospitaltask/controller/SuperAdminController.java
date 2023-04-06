package com.hospitaltask.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.hospitaltask.serviceImpl.EmailService;
import freemarker.template.TemplateException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.SuperAdminService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/super")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private SuperAdminRepo superAdminRepo;



    /*
     * SuperAdmin dtoToSuperUser(SuperUserDto superUserDto) { return
     * this.modelMapper.map(superUserDto, SuperAdmin.class); }
     */

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @NotNull SuperAdmin superAdmin) throws MessagingException, TemplateException, IOException {

        if (superAdmin.getRoles().getId() == 0) {
            return CustomResponseHandler.response("Select Role First", HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
        SuperAdmin superAdmin1 = superAdminService.save(superAdmin);
        if (superAdmin1 != null) {

            return CustomResponseHandler.response("Record Save", HttpStatus.CREATED, superAdmin1);
        } else {

            return CustomResponseHandler.response("Enter Valid Information", HttpStatus.BAD_REQUEST, null);
        }

    }


    @PostMapping("/verify/{email}/{token}")
    public ResponseEntity<?> accountVerify(@PathVariable String email, @PathVariable String token) {
        {
            SuperAdmin accountVerify = superAdminService.accountVerify(email, token);
            if (accountVerify != null) {
                return CustomResponseHandler.response("congratulations !! Your Account is Verify ", HttpStatus.ACCEPTED, email);
            } else {

                return CustomResponseHandler.response("Invalid Url ", HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        }

    }

    // TODO .... Under Process.....
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody SuperAdmin data, Long id) {
        SuperAdmin findData = superAdminRepo.findById(id).orElse(null);
        if (findData == null)
            return CustomResponseHandler.response("Enter Valid Id", HttpStatus.NOT_FOUND, id);
        else
            superAdminService.update(data, id);
        return CustomResponseHandler.response("Record Updated ", HttpStatus.OK, data);
    }

    @GetMapping("/getID/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        SuperAdmin superAdmin = superAdminService.findById(id);
        if (superAdmin != null)
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, superAdmin);
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        SuperAdmin superAdmin = superAdminService.findByEmail(email);
        if (superAdmin != null)
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, superAdmin);
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, email);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> findAll() {
        List<SuperAdmin> superAdmins = superAdminService.findAll();
        if (superAdmins != null)
            return CustomResponseHandler.response("Record Found " + "All User   " + superAdmins.size(), HttpStatus.OK,
                    superAdmins);
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, "null");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}/{aBoolean}")
    public ResponseEntity<?> findSuperUserByFlag(@PathVariable Integer id, @PathVariable Boolean aBoolean) {
        List<SuperAdmin> superAdmins = superAdminService.findSuperUserByFlag(id, aBoolean);
        if (superAdmins.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, superAdmins);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByEmail/{id}/{aBoolean}")
    public ResponseEntity<?> findSuperUserByFlag(@PathVariable String id, @PathVariable Boolean aBoolean) {
        List<SuperAdmin> superAdmins = superAdminService.findSuperAdminByEmailAndFlag(id, aBoolean);
        if (superAdmins.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, superAdmins);
    }

    @PutMapping("disableById/{id}")
    public ResponseEntity<?> disableById(@PathVariable Long id) {
        SuperAdmin superAdmin = superAdminService.findById(id);
        if (superAdmin != null) {
            superAdminService.disableById(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("enableById/{id}")
    public ResponseEntity<?> enableById(@PathVariable Long id) {
        SuperAdmin superAdmin = superAdminService.findById(id);
        if (superAdmin != null) {
            return CustomResponseHandler.response("Update Successfully ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("disableByEmail/{id}")
    public ResponseEntity<?> disableByEmail(@PathVariable String id) {
        SuperAdmin superAdmin = superAdminService.findByEmail(id);
        if (superAdmin != null) {
            superAdminService.disableByEmail(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("enableByEmail/{id}")
    public ResponseEntity<?> enableByEmail(@PathVariable String id) {
        SuperAdmin superAdmin = superAdminService.findByEmail(id);
        if (superAdmin != null) {
            superAdminService.enableByEmail(id);
            return CustomResponseHandler.response("Record enable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

}
