package com.hospitaltask.controller;

import java.util.List;

import com.hospitaltask.response.CustomResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.entity.Clinic;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.ClinicRepo;
import com.hospitaltask.service.ClinicService;

import javax.persistence.Id;


@RestController
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private ClinicRepo clinicRepo;

    // Add & Update Clinic
    //Add Clinic

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveClinic(@RequestBody Clinic clinic) {
        Clinic clinic1 = null;
        try {
            clinic1 = clinicService.saveClinic(clinic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clinic1 != null) return CustomResponseHandler.response("Create successfully ", HttpStatus.CREATED, clinic1);
        else {
            return CustomResponseHandler.response("Resolve Error ", HttpStatus.CREATED, null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("clinic/{id}")
    public ResponseEntity<Clinic> updateClinicById(@RequestBody Clinic clinic, @PathVariable Long id) {
        return new ResponseEntity<>(clinicService.updateClinicById(clinic, id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("clinic/name/{name}")
    public ResponseEntity<?> updateClinicByName(@RequestBody Clinic clinic, @PathVariable String name) {
        return CustomResponseHandler.response("Record updated ", HttpStatus.OK, clinicService.updateClinicByName(clinic, name));
    }

    // fetch All Clinic

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get-All/{aBoolean}")
    public ResponseEntity<?> getAllClinic(@PathVariable Boolean aBoolean) {
        List<Clinic> allEnableClinic = clinicService.getAllClinic(aBoolean);
        return CustomResponseHandler.response("Record Found " + "Total Find Record Founded : " + allEnableClinic.size(), HttpStatus.OK, allEnableClinic);
    }
    // fetch clinic By ClinicID

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/{id}/{aBoolean}")
    public ResponseEntity<?> getClinicById(@PathVariable Long id, @PathVariable Boolean aBoolean) throws UserNotFoundException {
        Clinic roles = this.clinicRepo.getClinicById(id);
        if (roles == null) return CustomResponseHandler.response("Record Not found", HttpStatus.NOT_FOUND, id);
        return CustomResponseHandler.response("Record Found ", HttpStatus.OK, clinicService.getClinicById(id, aBoolean));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}/{aBoolean}")
    public ResponseEntity<?> findClinicByFlag(@PathVariable Integer id, @PathVariable Boolean aBoolean) {
        List<Clinic> clinics = clinicService.findClinicByFlag(id, aBoolean);
        if (clinics.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, clinics);
    }


    // fetch clinic By ClinicName

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("get-clinicName/{clinicName}")
    public ResponseEntity<?> findByClinicName(@PathVariable String clinicName) {
        Clinic clinic = this.clinicRepo.findByClinicName(clinicName);
        if (clinic == null)
            return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, clinicName);
        return CustomResponseHandler.response("Record found ", HttpStatus.OK, clinicService.findByClinicName(clinicName));
    }

    // Delete All Clinic

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete-all")
    public String deleteAllClinic() {
        clinicService.deleteAllClinic();
        return "Clinic Deleted ";
    }
    //Delete clinic By ClinicId

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteByClinicById(@PathVariable Long id) throws UserNotFoundException {
        //   Clinic clinic = this.clinicService.getClinicById(id);
        //        if (clinic == null) return CustomResponseHandler.response("Record Not found ", HttpStatus.NOT_FOUND, id);
        //        else {
        clinicService.deleteClinicById(id);
        return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, id);
    }
}

