package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.*;
import com.hospitaltask.repository.*;
import com.hospitaltask.service.PatientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientEntityRepo patientRepo;

    Patient patient = null;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Add & Update Operation
    @Override
    public Patient save(@NotNull Patient patient) {
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
    public List<Patient> getAllPatient(boolean flag) {
        return patientRepo.findAllByFlag(flag);


    }

    public List<Patient> getAllPatient() {
        return patientRepo.findAll();
    }


    @Override
    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Override
    public Optional<Patient> findByDoctorID(Long id) {
        return patientRepo.findById(id);
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

    @Override
    public Patient disableById(long id) {
        Patient patient = disable(id, null);
        return patient;
    }

    @Override
    public Patient enableById(long id) {
        Patient patient = enable(id, null);
        return patient;
    }

    //TODO ....Delete By Email.....
    @Override
    public Patient disableByEmail(String id) {
        Patient patient = disable(0, id);
        return patient;
    }

    @Override
    public Patient enableByEmail(String id) {
        Patient patient = enable(0, id);
        return patient;
    }

    @Override
    public List<Patient> findPatientByFlag(Integer id, Boolean aBoolean) {
        return patientRepo.findPatientByFlag(id, aBoolean);
    }

    @Override
    public List<Patient> findPatientByEmailAndFlag(String id, Boolean aBoolean) {
        return patientRepo.findPatientByEmailAndFlag(id,aBoolean);
    }


    public Patient enable(long idL, String id) {
        if (idL > 0 && id == null) patient = getPatientById(idL);
        else patient = patientRepo.findByEmail(id);
        patient.setFlag(true);
        Patient save = patientRepo.save(patient);
        return save;
    }

    public Patient disable(long idL, String id) {
        if (idL > 0 && id == null) patient = getPatientById(idL);
        else patient = patientRepo.findByEmail(id);
        patient.setFlag(false);
        Patient save = patientRepo.save(patient);
        return save;
    }


}
