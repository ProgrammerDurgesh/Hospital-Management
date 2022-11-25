package com.hospitaltask.controller;

import com.hospitaltask.entity.Patient;
import com.hospitaltask.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public
class PatientController{
    @Autowired
    PatientService
            patientService;

    /*
    Add New patient
     */
    @PostMapping( "/patient" )
    public
    ResponseEntity < Patient > addNewPatient(@RequestBody Patient patient)
        {
            return new ResponseEntity <> ( patientService.addNewPatient ( patient ),HttpStatus.CREATED );
        }

    //this code is not Working
    @PutMapping( "/patient/{id}" )
    public
    ResponseEntity < Patient > updatePatientById(@RequestBody Patient patient,@PathVariable int id)
        {
            return new ResponseEntity <> ( patientService.updatePatientById ( patient,id ),HttpStatus.CREATED );
        }

    //this code is not Working
    @PutMapping( "/patient/email/{emailId}" )
    public
    ResponseEntity < Patient > updatePatientByEmailId(@RequestBody Patient patient,@PathVariable String emailId)
        {
            return new ResponseEntity <> ( patientService.updatePatientByEmailId ( patient,emailId ),HttpStatus.CREATED );
        }

    /*
    fetch All Patient
     */
    @GetMapping( "/patient" )
    public
    ResponseEntity < List < Patient > > getAllPatient()
        {
            return new ResponseEntity <> ( patientService.getAllPatient ( ),HttpStatus.OK );
        }

    /*
    Fetch All Patient
     */
    @GetMapping( "/patient/{id}" )
    public
    ResponseEntity < Patient > getPatientById(@PathVariable Long id)
        {
            return new ResponseEntity <> ( patientService.getPatientById ( id ),HttpStatus.OK );
        }


    /*
    delete patient By ID
     */
    @DeleteMapping( "patient/{patientId}" )
    public
    void deletePatientByID(@PathVariable Long patientId)
        {
            this.patientService.deletePatientByID ( patientId );
        }

        /*
        Delete All Patient
         */

    @DeleteMapping( "patient" )
    public
    void deleteAllPatient()
        {
            this.patientService.deleteAllPatient ( );
        }

    /*
    Fetch patient By Email
     */
    @GetMapping( "/patient/email/{email}" )
    public
    ResponseEntity < Patient > findByEmail(@PathVariable String email)
        {
            return new ResponseEntity <> ( patientService.findByEmail ( email ),HttpStatus.OK );
        }


    /*
      Fetch patient By Name
       */
    @GetMapping( "/patient/name/{name}" )
    public
    ResponseEntity < Patient > findByName(@PathVariable String name)
        {
            return new ResponseEntity <> ( patientService.findByName ( name ),HttpStatus.OK );
        }


    /*
      Fetch patient By DoctorID
       */
    @GetMapping( "/patient/doctor/{doctor}" )
    public
    ResponseEntity < ? > findByByDoctorId(@PathVariable Long doctor)
        {
            System.out.println ( "patient search by doctor id " );
            List < Patient > patients=patientService.findByDoctorID ( doctor );
            return new ResponseEntity <> ( patients,HttpStatus.OK );
        }
}
/*
    @GetMapping ( "/patient/doctor/{id}" )
    public ResponseEntity < Patient > getPatientByDoctorId (@PathVariable Long doctorId ) {
        return new ResponseEntity <> ( patientService.getPatientByDoctorId ( doctorId ) , HttpStatus.OK );
    }



    @GetMapping ( "/patient/clinicID{clinicID}" )
    public ResponseEntity < Patient > getPatientByClinicId (@PathVariable String clinicID ) {
        return new ResponseEntity <> ( patientService.getPatientByClinicId ( clinicID ) , HttpStatus.OK );
    }


    @DeleteMapping ( "patient/emailId{emailId}" )
    public void deletePatientIdByEmailID (@PathVariable String emailId ) {
        this.patientService.deletePatientIdByEmailID ( emailId );

    }

    @DeleteMapping ( "patient/clinicCode{clinicCode}" )
    public void deletePatientByClinicCode (@PathVariable String clinicCode ) {
        this.patientService.deletePatientByClinicCode ( clinicCode );
    }
*/



