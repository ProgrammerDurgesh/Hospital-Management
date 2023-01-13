package com.hospitaltask.controller;

import java.util.List;
import java.util.Optional;

import com.hospitaltask.response.CustomResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientEntityRepo entityRepo;
	@Autowired
	PatientService patientService;
	@Autowired
	private DoctorRepo doctorRepo;


	/*
	 * Add & Update Patient operation
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Patient patient) {
		System.out.println("This is Patient Save Page ");

		System.out.println("Patient Controller :::      "+patient.toString());
		String exceptionShow=null;
		Doctor doctor1 = patient.getDoctor();
		Optional<Doctor> doctor=doctorRepo.findById(doctor1.getDoctorId());
		if(doctor.isEmpty()) {
			exceptionShow = "Doctor Not found";
		return  new ResponseEntity<>(exceptionShow,HttpStatus.NOT_FOUND);
		}


		Patient patient1 = patientService.findByEmail(patient.getEmail());
		if(patient1 !=null)
		{
			exceptionShow="Email Already Exists";
			return new ResponseEntity<>(exceptionShow, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
	}

	// TODO Under process.....
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	@PutMapping("/patient/{id}")
	public ResponseEntity<?> updatePatientById(@RequestBody Patient patient, @PathVariable Long id) {
		Patient patientUpdate = patientService.updatePatientById(patient, id);
		if (patientUpdate == null)
			return CustomResponseHandler.response("Record Not  Found ",HttpStatus.NOT_FOUND,id);
		return CustomResponseHandler.response("created successfully",HttpStatus.CREATED,patientService.updatePatientById(patient, id));
	}

	/*
	 * fetch & filter Patient
	 */
//	@RolesAllowed("ROLE_DOCTOR")

	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	@GetMapping("/p-all")
	public ResponseEntity<?> getAllPatient() {
		List<Patient> list=patientService.getAllPatient();
		if(list.size() !=0)
		{
		return CustomResponseHandler.response(" Patient List  : "+"Total Patient "+list.size(),HttpStatus.OK,list);
		}
		return CustomResponseHandler.response("Record Not Found ",HttpStatus.OK,list);
	}

	/*
	 * fetch Patient By PatientEmailId
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')") 
	@GetMapping({"/patient/{id}","p-Id/{id}"})
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {
		Patient patient=this.entityRepo.findById(id).orElse(null);
		if(patient==null)
		{
			return CustomResponseHandler.response("Record Not Found ",HttpStatus.NOT_FOUND,id);
		}
		return  CustomResponseHandler.response("Record Found Success",HttpStatus.OK,patientService.getPatientById(id));
	}

	/*
	 * Fetch patient By Email
	 */
/*
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')")
	@GetMapping({"/patient/email/{email}","p-email/{email}"})
	public ResponseEntity<Patient> findByEmail(@PathVariable String email) {
		return new ResponseEntity<>(patientService.findByEmail(email), HttpStatus.OK);
	}
*/

	/*
	 * Fetch patient By Name
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')") 
	@GetMapping("/patient/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) {
		List<Patient> patient = patientService.findByName(name);
		if(patient==null)
			return CustomResponseHandler.response("Record Not Found ",HttpStatus.NOT_FOUND,name);
		return CustomResponseHandler.response("Record Found Success",HttpStatus.OK,patientService.findByName(name));
	}

	/*
	 * Fetch patient By DoctorID
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')") 
	@GetMapping("/patient/doctor/{doctorId}")
	public ResponseEntity<?> findByByDoctorId(@PathVariable Long doctorId) {
			List<Patient> patients = entityRepo.findAllPatientByDoctorId(doctorId);
				if (patients.size() == 0)
					return CustomResponseHandler.response("Record Not Found",HttpStatus.NOT_FOUND,doctorId);
					return CustomResponseHandler.response("Record Found Success",HttpStatus.OK,patients);
	}

	// Delete Operations
	/*
	 * Delete All Patient
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("patient")
	public ResponseEntity<?> deleteAllPatient() {
		this.patientService.deleteAllPatient();
		List<Patient> patient=patientService.getAllPatient();
		if(patient.size()==0)
		return CustomResponseHandler.response("Delete All Record",HttpStatus.OK,patient);
		return CustomResponseHandler.response("Record Not Deleted ",HttpStatus.EXPECTATION_FAILED,patient);
	}

	/*
	 * Delete Patient By PatientId
	 */
	@PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')") 
	@DeleteMapping("patient/patient/{Id}")
	public ResponseEntity<?> deletePatientByID(@PathVariable Long Id) {
		Patient patient = entityRepo.findById(Id).orElse(null);
		if (patient == null)
			return CustomResponseHandler.response("Record Not Found",HttpStatus.NOT_FOUND,Id);
		else {
			this.patientService.deletePatientByID(Id);
			return CustomResponseHandler.response("Record Deleted ",HttpStatus.OK,patient.getId());
		}

	}

}
/*
 * @GetMapping ( "/patient/doctor/{id}" ) public ResponseEntity < Patient >
 * getPatientByDoctorId (@PathVariable Long doctorId ) { return new
 * ResponseEntity <> ( patientService.getPatientByDoctorId ( doctorId ) ,
 * HttpStatus.OK ); }
 * 
 * @GetMapping ( "/patient/email{email}" ) public ResponseEntity < Patient >
 * getPatientByEmailId (@PathVariable String email ) { return new ResponseEntity
 * <> ( patientService.getPatientByEmailId ( email ) , HttpStatus.OK ); }
 * 
 * @GetMapping ( "/patient/clinicID{clinicID}" ) public ResponseEntity < Patient
 * > getPatientByClinicId (@PathVariable String clinicID ) { return new
 * ResponseEntity <> ( patientService.getPatientByClinicId ( clinicID ) ,
 * HttpStatus.OK ); }
 * 
 * 
 * @DeleteMapping ( "patient/emailId{emailId}" ) public void
 * deletePatientIdByEmailID (@PathVariable String emailId ) {
 * this.patientService.deletePatientIdByEmailID ( emailId );
 * 
 * }
 * 
 * @DeleteMapping ( "patient/clinicCode{clinicCode}" ) public void
 * deletePatientByClinicCode (@PathVariable String clinicCode ) {
 * this.patientService.deletePatientByClinicCode ( clinicCode ); }
 * 
 * this code is not Working
 * 
 * @PutMapping ( "/patient/email/{emailId}" ) public ResponseEntity < Patient >
 * updatePatientByEmailId (@RequestBody Patient patient,@PathVariable String
 * emailId ) { return new ResponseEntity <> (
 * patientService.updatePatientByEmailId ( patient, emailId ) ,
 * HttpStatus.CREATED ); }
 *
 * 
 * 
 * 
 */
