package com.hospitaltask.controller;

import com.hospitaltask.entity.Clinic;
import com.hospitaltask.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public
class ClinicController{

    @Autowired
    ClinicService
            clinicService;

    /*
    Add New Clinic Name
     */
    @PostMapping( "/clinic" )
    public
    ResponseEntity < Clinic > saveClinic(@RequestBody Clinic clinic)
        {
            return new ResponseEntity <> ( clinicService.saveClinic ( clinic ),HttpStatus.CREATED );
        }

    /*
    Fetch All  Clinic Name
     */
    @GetMapping( "/clinic" )
    public
    ResponseEntity < List < Clinic > > getAllClinic()
        {
            return new ResponseEntity <> ( clinicService.getAllClinic ( ),HttpStatus.OK );
        }

    /*
    Fetch Clinic Name By Clinic ID
    */
    @GetMapping( "/clinic/{id}" )
    public
    ResponseEntity < Clinic > getClinicById(@PathVariable Long id)
        {
            return new ResponseEntity <> ( clinicService.getClinicById ( id ),HttpStatus.OK );
        }

        /*
        Fetch Clinic By ClinicName
        */

    @GetMapping( "/clinic/clinicName/{clinicName}" )
    public
    ResponseEntity < Clinic > findByClinicName(@PathVariable String clinicName)
        {
            System.out.println ( "This is clinicName method" );
            return new ResponseEntity <> ( clinicService.findByClinicName ( clinicName ),HttpStatus.OK );
        }




}
