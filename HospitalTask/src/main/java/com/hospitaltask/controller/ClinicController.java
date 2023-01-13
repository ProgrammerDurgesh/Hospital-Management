package com.hospitaltask.controller;

import java.util.List;

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

@RestController
@RequestMapping("/HM/clinic")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	@Autowired
	private ClinicRepo clinicRepo;

	// Add & Update Clinic
	/*
	 * Add Clinic
	 */

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<Clinic> saveClinic(@RequestBody Clinic clinic) {
		return new ResponseEntity<>(clinicService.saveClinic(clinic), HttpStatus.CREATED);
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("clinic/{id}")
	public ResponseEntity<Clinic> updateClinicById(@RequestBody Clinic clinic, @PathVariable Long id) {
		return new ResponseEntity<>(clinicService.updateClinicById(clinic, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("clinic/name/{name}")
	public ResponseEntity<Clinic> updateClinicByName(@RequestBody Clinic clinic, @PathVariable String name) {
		return new ResponseEntity<>(clinicService.updateClinicByName(clinic, name), HttpStatus.OK);
	}

	/*
	 * fetch All Clinic
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/get-All")
	public ResponseEntity<List<Clinic>> getAllClinic() {
		return new ResponseEntity<>(clinicService.getAllClinic(), HttpStatus.OK);
	}
	/*
	 * fetch clinic By ClinicID
	 */

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getClinicById(@PathVariable Long id) throws UserNotFoundException {
		Clinic roles = this.clinicRepo.getClinicById(id);
		if (roles == null)
			return new ResponseEntity<>("Clinic Not Found   " + id, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(clinicService.getClinicById(id), HttpStatus.OK);
	}

	/*
	 * fetch clinic By ClinicName
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("get-clinicName/{clinicName}")
	public ResponseEntity<?> findByClinicName(@PathVariable String clinicName) {
		Clinic clinic = this.clinicRepo.findByClinicName(clinicName);
		if (clinic == null)
			return new ResponseEntity<>("Clinic Not Found " + clinicName, HttpStatus.OK);
		return new ResponseEntity<>(clinicService.findByClinicName(clinicName), HttpStatus.OK);
	}

	/*
	 * Delete All Clinic
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete-all")
	public String deleteAllClinic() {
		clinicService.deleteAllClinic();
		return "Clinic Deleted ";
	}
	/*
	 * Delete clinic By ClinicId
	 */

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteByClinicById(@PathVariable Long id) throws UserNotFoundException {
		Clinic clinic = this.clinicService.getClinicById(id);
		if (clinic==null) {
			return new ResponseEntity<>("Clinic Not Found" + id, HttpStatus.OK);}
		else {
			clinicService.deleteClinicById(id);
			return new ResponseEntity<>("Deleted " + id, HttpStatus.OK);
		}
	}
}
