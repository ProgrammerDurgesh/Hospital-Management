package com.hospitaltask.controller;

import com.hospitaltask.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospitaltask.entity.*;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService
            doctorService;

    //Only For Test Purpose
    @GetMapping ( "/home" )
    public
    String home ( )
        {
            return "this is home page ";
        }

    /*
    Add Doctor
     */
    @PostMapping ( "/doctor" )
    public
    ResponseEntity < Doctor > addDoctor ( @RequestBody Doctor doctor )
        {
            return new ResponseEntity ( this.doctorService.addDoctor ( doctor ) , HttpStatus.CREATED );
        }
    /*
    fetch All Doctor
     */

    @GetMapping ( "/doctor" )
    public
    ResponseEntity < List < Doctor > > getAllDoctor ( )
        {
            return new ResponseEntity <> ( this.doctorService.getAllDoctor ( ) , HttpStatus.OK );
        }

    /*
    fetch Doctor By DoctorById
     */
    @GetMapping ( "/doctor/findById/{id}" )
    public
    ResponseEntity < Doctor > getDoctorByID ( @PathVariable Long id )
        {
            return new ResponseEntity < Doctor > ( this.doctorService.getDoctorById ( id ) , HttpStatus.OK );
        }

    /*
    fetch Doctor By DoctorEmailID
     */
    @GetMapping ( "doctor/{id}" )
    public
    ResponseEntity < Doctor > findByEmailId ( @PathVariable String id )
        {
            return new ResponseEntity <> ( this.doctorService.findByEmail ( id ) , HttpStatus.OK );
        }
    /*
    fetch Doctor By DoctorByName
     */

    @GetMapping ( "doctor/doctorName/{doctorName}" )
    public
    ResponseEntity < Doctor > findByDoctorName ( @PathVariable String doctorName )
        {
            return new ResponseEntity <> ( this.doctorService.findByDoctorName ( doctorName ) , HttpStatus.OK );
        }


        //Delete Operation
    /*
    Delete All Doctor
     */
    @DeleteMapping("doctor")
    public String deleteAllDoctor()
        {
            doctorService.deleteAllDoctor ();
            return "Doctor Deleted";
        }
        /*
        Delete Doctor By DoctorId
         */
    @DeleteMapping("doctor/{id}")
    public String deleteDoctorById(Long id)
        {
            doctorService.deleteDoctorById (id);
            return "Doctor Deleted";
        }

}
