package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.*;
import com.hospitaltask.repository.*;
import com.hospitaltask.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientEntityRepo patientRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	// Add & Update Operation
	@Override
	public Patient save(Patient patient) {
		Patient save = null;
		Patient email = patientRepo.findByEmail(patient.getEmail());
		if (email == null) {
			patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
			save = patientRepo.save(patient);
			return save;
		}


		return save;
	}

	@Override
	public Patient updatePatientById(Patient patient, Long id) {
		Patient save = null;
		Patient patient2 = patientRepo.findById(id).orElse(null);
		if (patient2 == null) {
			return patient2;
		} else {
			patient2.setName(patient.getName());
			patient2.setEmail(patient.getEmail());
			patient2.setDoctor(patient.getDoctor());
			patient2.setAge(patient.getAge());
			patient2.setBloodGroup(patient.getBloodGroup());
			patient2.setIllness(patient.getIllness());
			patient2.setPassword(patient.getPassword());
			save = patientRepo.save(patient2);
		}

		return save;
	}

	@Override
	public Patient updatePatientByName(Patient patient, String clinicName) {
		return null;
	}

	// fetch & Filter Operation

	@Override
	public List<Patient> getAllPatient() {
		return patientRepo.findAll();
	}

	@Override
	public Patient getPatientById(Long id) {
		return patientRepo.findById(id).get();
	}

	@Override
	public Optional<Patient> findByDoctorID(Long id) {
		return  patientRepo.findById(id);
	}

	@Override
	public List<Patient> findByName(String name) {
		return patientRepo.findByName(name);
	}

	@Override
	public Patient findByEmail(String email) {
		return patientRepo.findByEmail(email);
	}

	// Delete Operation

	@Override
	public void deleteAllPatient() {
		this.patientRepo.deleteAll();
	}

	@Override
	public void deletePatientByID(Long patientId) {

		this.patientRepo.deleteById(patientId);
	}

}
