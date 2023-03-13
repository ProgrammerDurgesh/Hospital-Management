package com.hospitaltask.controller;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.PatientService;
import com.hospitaltask.serviceImpl.SendEmailTemplate;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    
    @Autowired
	private SendEmailTemplate emailTemplate;

    @Autowired
    private PatientEntityRepo entityRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    //Add & Update Patient operation
  
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @NotNull Patient patient) {
        Doctor doctor1 = patient.getDoctor();
        Optional<Doctor> doctor = doctorRepo.findById(doctor1.getDoctorId());
        if (doctor.isEmpty()) {
            return CustomResponseHandler.response("Doctor Not found ", HttpStatus.NOT_FOUND, doctor);
        }
        Patient patient1 = patientService.findByEmail(patient.getEmail());
        if (patient1 != null) {
            return CustomResponseHandler.response("Email Already Exists ", HttpStatus.OK, patient.getEmail());
        } else {
        	String message = "Your Are Registered with Appollo Hospital ";

			String obj = "Registration Details " + "\n" + "\n" + "Email : " + patient.getEmail() + "\n" + "Password : "
					+ patient.getPassword();
			Patient save = patientService.save(patient);

			emailTemplate.sendAttached(obj, message, patient.getEmail());
            
            return CustomResponseHandler.response("Data Saved ", HttpStatus.CREATED, save);
        }
    }
    
    
    @PostMapping("/verify/{email}/{token}")
	public ResponseEntity<?> acountVerify(@PathVariable String email,@PathVariable String token) {
		{
			Patient acountVerify = patientService.acountVerify(email, token);
			if(acountVerify !=null)
			{
				return CustomResponseHandler.response("Congrachulation !! Your Account is Varify ",HttpStatus.ACCEPTED,email);
			}
			else 
			{
			
				return CustomResponseHandler.response("Invalid Url ",HttpStatus.INTERNAL_SERVER_ERROR,null );
			}
			
		}
		
	}

    @PostMapping("/forgot-password/{email}")
    public String forgotPassword(@PathVariable String email){

        String response =  patientService.forgotPassword(email);

        if(!response.startsWith("Invalid")){
            response = "http://localhost:8000/reset-password?token=" + response;
        }

        return response;
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token,@RequestParam String password){
        String s = patientService.resetPassword(token, password);
        return CustomResponseHandler.response(s,HttpStatus.OK,null);
    }


    // TODO Under process.....
    
    @PutMapping("/patient/{id}")
    public ResponseEntity<?> updatePatientById(@RequestBody Patient patient, @PathVariable Long id) {
        Patient patientUpdate = patientService.updatePatientById(patient, id);
        if (patientUpdate == null)
            return CustomResponseHandler.response("Record Not  Found ", HttpStatus.NOT_FOUND, id);
        return CustomResponseHandler.response("Record Update successfully", HttpStatus.OK, patientService.updatePatientById(patient, id));
    }

    //fetch & filter Patient
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @GetMapping("/p-all/{flag}")
    public ResponseEntity<?> getAllPatient(@PathVariable boolean flag) {
        List<Patient> list = patientService.getAllPatient(flag);
        if (list.size() != 0)
            return CustomResponseHandler.response(" Patient List  : " + "Total Patient " + list.size(), HttpStatus.OK, list);
        return CustomResponseHandler.response("Record Not Found ", HttpStatus.OK, list);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}/{aBoolean}")
    public ResponseEntity<?> findPatientByFlag(@PathVariable Integer id, @PathVariable Boolean aBoolean) {
        List<Patient> patient = patientService.findPatientByFlag(id, aBoolean);
        if (patient.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else return CustomResponseHandler.response("Record Found", HttpStatus.OK, patient);
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByEmail/{id}/{aBoolean}")
    public ResponseEntity<?> findPatientByFlag(@PathVariable String id, @PathVariable Boolean aBoolean) {
        List<Patient> patient = patientService.findPatientByEmailAndFlag(id,aBoolean);
        if (patient.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else return CustomResponseHandler.response("Record Found", HttpStatus.OK, patient);
    }

    // fetch Patient By PatientEmailId
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')")
    @GetMapping({"/patient/{id}", "p-Id/{id}"})
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Patient patient = this.entityRepo.findById(id).orElse(null);
        if (patient == null) {
            return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, id);
        }
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, patientService.getPatientById(id));
    }
    //TODo under working .........

    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')")
    @GetMapping({"/patient/email/{email}", "p-email/{email}"})
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        Patient byEmail = patientService.findByEmail(email);
        if (byEmail != null) return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, byEmail);
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, email);
    }


    // Fetch patient By Name
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')")
    @GetMapping("/patient/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        List<Patient> patient = patientService.findByName(name);
        if (patient == null) return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, name);
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, patientService.findByName(name));
    }

    //Fetch patient By DoctorID
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN,ROLE_PATIENT')")
    @GetMapping("/patient/doctor/{doctorId}")
    public ResponseEntity<?> findByByDoctorId(@PathVariable Long doctorId) {
        List<Patient> patients = entityRepo.findAllPatientByDoctorId(doctorId);
        if (patients.size() == 0)
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, doctorId);
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, patients);
    }

    // Delete Operations
    //Delete All Patient
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("patient")
    public ResponseEntity<?> deleteAllPatient() {
        this.patientService.deleteAllPatient();
        List<Patient> patient = patientService.getAllPatient();
        if (patient.size() == 0) return CustomResponseHandler.response("Delete All Record", HttpStatus.OK, patient);
        return CustomResponseHandler.response("Record Not Deleted ", HttpStatus.EXPECTATION_FAILED, patient);
    }


    //Delete Patient By PatientId
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("patient/patient/{Id}")
    public ResponseEntity<?> deletePatientByID(@PathVariable Long Id) {
        Patient patient = entityRepo.findById(Id).orElse(null);
        if (patient == null) return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, Id);
        else {
            this.patientService.deletePatientByID(Id);
            return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, patient.getId() + " " + patient.getEmail() + " " + patient.getName());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @PutMapping("disableById/{id}")
    public ResponseEntity<?> disableById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patientService.disableById(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @PutMapping("enableById/{id}")
    public ResponseEntity<?> enableById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patientService.enableById(id);
            return CustomResponseHandler.response("Update Successfully ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @PutMapping("disableByEmail/{id}")
    public ResponseEntity<?> disableByEmail(@PathVariable String id) {
        Patient patient = patientService.findByEmail(id);
        if (patient != null) {
            patientService.disableByEmail(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @PutMapping("enableByEmail/{id}")
    public ResponseEntity<?> enableByEmail(@PathVariable String id) {
        Patient patient = patientService.findByEmail(id);
        if (patient != null) {
            patientService.enableByEmail(id);
            return CustomResponseHandler.response("Record enable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
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
 */
