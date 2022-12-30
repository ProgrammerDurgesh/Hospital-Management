package com.hospitaltask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;

@RestController
@RequestMapping("HM/doctor")
public class DoctorController { 	
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private DoctorService doctorService;

	/*
	 * Add Doctor
	 */
	@PostMapping("/save")
	public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
		String email = doctorRepo.getEmailByEmai(doctor.getEmail());
		if (email != null)
			return new ResponseEntity<>("Email already exist :  " + doctor.getEmail(), HttpStatus.OK);
		return new ResponseEntity<>(this.doctorService.addDoctor(doctor), HttpStatus.CREATED);
	}

	/*
	 * Update Doctor By DoctorName/DoctorEmail/DoctorID
	 * 
	 */
	@PutMapping("/save/Id/{id}")
	public ResponseEntity<?> updateById(@RequestBody Doctor doctor, @PathVariable Long id) {
		Doctor doctorCheck = doctorService.updateDoctorById(doctor, id);
		if (doctorCheck != null) {
			return new ResponseEntity<>("Doctor Update By :  " + id + doctor.getEmail(), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Doctor Not Availeble This id :" + id, HttpStatus.NOT_FOUND);
	}

	/*@PutMapping("/save-email/{email}")
	public ResponseEntity<?> updateByEmail(@RequestBody Doctor doctor, @PathVariable String email) {
		Doctor doctorCheck = doctorRepo.findByEmail(email);
		if (!doctorCheck.equals(null)) {
			doctorService.updateDoctorByEmail(doctor, email);
			return new ResponseEntity<>("Doctor Update By :  " + email + doctor.getEmail(), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Doctor Not Availeble This Email :" + email, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/save-name/{name}")
	public ResponseEntity<?> updateByName(@RequestBody Doctor doctor, @PathVariable String name) {
		Doctor doctorCheck = doctorRepo.findByName(name);
		if (!doctorCheck.equals(null)) {
			doctorService.updateDoctorByEmail(doctor, name);
			return new ResponseEntity<>("Doctor Update By :  " + name + doctor.getEmail(), HttpStatus.OK);
		} else
			return new ResponseEntity<>("Doctor Not Availeble This Email :" + name, HttpStatus.NOT_FOUND);
	}
*/
	/*
	 * fetch All Doctor
	 */
	@GetMapping("/All")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		return new ResponseEntity<>(this.doctorService.getAllDoctor(), HttpStatus.OK);
	}

	/*
	 * fetch Doctor By DoctorById
	 */
	@GetMapping("/get-id/{id}")
	public ResponseEntity<?> getDoctorByID(@PathVariable Long id) {
		Doctor doctor = this.doctorRepo.findById(id).orElse(null);
		if (doctor == null)
			return new ResponseEntity<>("Doctor Not found with this Id " + id, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Doctor>(this.doctorService.getDoctorById(id), HttpStatus.OK);
	}

	/*
	 * fetch Doctor By DoctorEmailID
	 */
	@GetMapping("get-email/{id}")
	public ResponseEntity<?> findByEmailId(@PathVariable String id) {
		System.out.println(id);
		Doctor doctor = this.doctorRepo.getDoctorByEmail(id);
		if (doctor == null)
			return new ResponseEntity<>("Doctor Not found ", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(this.doctorService.findByEmail(id), HttpStatus.OK);
	}
	/*
	 * fetch Doctor By DoctorByName
	 */

	@GetMapping("get/{doctorName}")
	public ResponseEntity<?> findByDoctorName(@PathVariable String doctorName) {
		List<Doctor> doctor = this.doctorRepo.findByDoctorName(doctorName);
		if (doctor.size() == 0)
			return new ResponseEntity<>("Not Found", HttpStatus.OK);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	/*
	 * Delete Operation Delete All Doctor
	 */
	@DeleteMapping("Delete-All")
	public String deleteAllDoctor() {
		doctorService.deleteAllDoctor();
		return "Doctor Deleted";
	}

	/*
	 * Delete Doctor By DoctorId
	 */
	@DeleteMapping("Delete/{id}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable Long id) throws UserNotFoundException {
		Doctor doctor = doctorRepo.findById(id).orElse(null);
		if (doctor == null)
			return new ResponseEntity<>("Doctor Not Found This Id : " + id, HttpStatus.NOT_FOUND);
		else {
			doctorService.deleteDoctorById(id);
			return new ResponseEntity<>("Doctor Deleted : " + id, HttpStatus.OK);
		}
	}

	@DeleteMapping("Delete/name/{name}")
	public ResponseEntity<?> deleteDoctorByName(@PathVariable String name) throws UserNotFoundException {
		Doctor doctor = doctorRepo.findByName(name);
		if (doctor == null)
			return new ResponseEntity<>("Doctor Not Found This Id : " + name, HttpStatus.NOT_FOUND);
		else {
			doctorService.deleteDoctorById(doctor.getDoctorId());
			return new ResponseEntity<>("Doctor Deleted : " + name, HttpStatus.OK);
		}
	}

	@DeleteMapping("Delete/email/{id}")
	public ResponseEntity<?> deleteDoctorByEmail(@PathVariable String id) throws UserNotFoundException {
		Doctor doctor = doctorRepo.getDoctorByEmail(id);
		if (doctor == null)
			return new ResponseEntity<>("Doctor Not Found This Id : " + id, HttpStatus.NOT_FOUND);
		else {
			doctorService.deleteDoctorById(doctor.getDoctorId());
			return new ResponseEntity<>("Doctor Deleted : " + id, HttpStatus.OK);
		}
	}
}
