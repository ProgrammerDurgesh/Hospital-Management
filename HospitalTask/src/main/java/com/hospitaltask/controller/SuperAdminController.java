package com.hospitaltask.controller;

import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sup")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private SuperAdminRepo superAdminRepo;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin superAdmin1 = superAdminService.save(superAdmin);
        if (superAdmin1 != null)
            return CustomResponseHandler.response("Record Save", HttpStatus.CREATED, superAdmin1);
        return CustomResponseHandler.response("Enter Valid Information", HttpStatus.BAD_REQUEST, superAdmin);
    }

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

    @GetMapping("/get/{id}")
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
            return CustomResponseHandler.response("Record Founded " + "All User   " + superAdmins.size()
                    , HttpStatus.OK, superAdmins);
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, "null");
    }

    @DeleteMapping("deleteId/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        SuperAdmin superAdmin = superAdminService.findById(id);
        if (superAdmin != null) {
            superAdminService.deleteById(id);
            return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String id) {
        SuperAdmin superAdmin = superAdminService.findByEmail(id);
        if (superAdmin != null) {
            superAdminService.deleteByEmail(id);
            return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }


}
