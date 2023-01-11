package com.hospitaltask.controller;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAuthority('ROLE_PATIENT')")
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
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Patient patient) {

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

	// this code is not Working
	@PutMapping("/patient/{id}")
	public ResponseEntity<?> updatePatientById(@RequestBody Patient patient, @PathVariable Long id) {
		Patient patientUpdate = patientService.updatePatientById(patient, id);
		if (patientUpdate == null)
			return new ResponseEntity<>("Patient Not Found :  " + "id :" + id + "	Email : " + patient.getEmail(),
					HttpStatus.CREATED);
		return new ResponseEntity<>(patientService.updatePatientById(patient, id), HttpStatus.CREATED);
	}

	/*
	 * fetch & filter Patient
	 */
	@GetMapping("/p-all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
	}

	/*
	 * fetch Patient By PatientEmailId
	 */
	@GetMapping({"/patient/{id}","p-Id/{id}"})
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {
		Patient patient=this.entityRepo.findById(id).orElse(null);
		if(patient==null)
		{
			return new ResponseEntity<>("Patient Not Found :    "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
	}

	/*
	 * Fetch patient By Email
	 */
	@GetMapping({"/patient/email/{email}","p-email/{email}"})
	public ResponseEntity<Patient> findByEmail(@PathVariable String email) {
		return new ResponseEntity<>(patientService.findByEmail(email), HttpStatus.OK);
	}

	/*
	 * Fetch patient By Name
	 */
	@GetMapping("/patient/name/{name}")
	public ResponseEntity<Patient> findByName(@PathVariable String name) {
		return new ResponseEntity<>(patientService.findByName(name), HttpStatus.OK);
	}

	/*
	 * Fetch patient By DoctorID
	 */
	@GetMapping("/patient/doctor/{doctorId}")
	public ResponseEntity<?> findByByDoctorId(@PathVariable Long doctorId) {
		List<Patient> patients = entityRepo.findAllPatientByDoctorId(doctorId);
		if (patients.size() == 0)
			return new ResponseEntity<>("Patient Not Found" + doctorId, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	// Delete Operations
	/*
	 * Delete All Patient
	 */
	@DeleteMapping("patient")
	public void deleteAllPatient() {
		this.patientService.deleteAllPatient();
	}

	/*
	 * Delete Patient By PatientId
	 */
	@DeleteMapping("patient/patient/{Id}")
	public ResponseEntity<?> deletePatientByID(@PathVariable Long Id) {
		Patient patient = entityRepo.findById(Id).orElse(null);
		if (patient == null)
			return new ResponseEntity<>("Patient Not Found : " + Id, HttpStatus.NOT_FOUND);
		else {
			this.patientService.deletePatientByID(Id);
			return new ResponseEntity<>("Patient Deleted : " + Id, HttpStatus.OK);
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
