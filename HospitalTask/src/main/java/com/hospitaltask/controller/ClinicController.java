package com.hospitaltask.controller;

import com.hospitaltask.entity.*;
import com.hospitaltask.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HM")
public class ClinicController {


    @Autowired
    ClinicService
            clinicService;

    // Add & Update Clinic
    /*
    Add Clinic
     */

    @PostMapping ( "/clinic")
    public
    ResponseEntity < Clinic > saveClinic ( @RequestBody Clinic clinic )
        {
            return new ResponseEntity <> ( clinicService.saveClinic ( clinic ) , HttpStatus.CREATED );
        }

    @PutMapping ( "clinic/{id}")
    public
    ResponseEntity < Clinic > updateClinicById ( @RequestBody Clinic clinic , @PathVariable Long id )
        {
            return new ResponseEntity <> ( clinicService.updateClinicById ( clinic , id ) , HttpStatus.OK );
        }

    @PutMapping ( "clinic/name/{name}")
    public
    ResponseEntity < Clinic > updateClinicByName ( @RequestBody Clinic clinic , @PathVariable String name )
        {
            return new ResponseEntity <> ( clinicService.updateClinicByName ( clinic , name ) , HttpStatus.OK );
        }

    /*
    fetch All Clinic
    */
    @GetMapping ("/clinic")
    public ResponseEntity < List < Clinic > > getAllClinic ( )
        {
            System.out.println ("Hello google" );
            return new ResponseEntity <> ( clinicService.getAllClinic (), HttpStatus.OK );
        }
        /*
         fetch clinic By ClinicID
         */

    @GetMapping ( "/clinic/{id}")
    public
    ResponseEntity < Clinic > getClinicById ( @PathVariable Long id )
        {
            return new ResponseEntity <> ( clinicService.getClinicById ( id ) , HttpStatus.OK );
        }

    /*
    fetch clinic By ClinicName
    */
    @GetMapping ( "/clinic/clinicName/{clinicName}")
    public
    ResponseEntity < Clinic > findByClinicName ( @PathVariable String clinicName )
        {
            return new ResponseEntity <> ( clinicService.findByClinicName ( clinicName ) , HttpStatus.OK );
        }

    /*
    Delete All Clinic
     */
    @DeleteMapping ( "/clinic")
    public
    String deleteAllClinic ( )
        {
            clinicService.deleteAllClinic ( );
            return "Clinic Deleted ";
        }
        /*
       Delete clinic By ClinicId
     */

    @DeleteMapping ( "clinic/{id}")
    public
    String deleteByClinicById ( @PathVariable Long id )
        {
            clinicService.deleteClinicById ( id );
            return "Clinic Deleted ";
        }

}
