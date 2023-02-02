package com.hospitaltask.service;

import com.hospitaltask.entity.*;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    //Add & Update Operation

    Patient save(Patient patient);

    Patient updatePatientById(Patient patient, Long id);

    Patient updatePatientByName(Patient patient, String name);


    //Fetch & filter Operation

    List<Patient> getAllPatient(boolean flag);

    List<Patient> getAllPatient();


    Patient getPatientById(Long id);

    Patient findByEmail(String email);

    Optional<Patient> findByDoctorID(Long id);

    List<Patient> findByName(String name);

    //Delete Operation

    void deleteAllPatient();

    void deletePatientByID(Long patientId);


    //Enable & disable
    Patient disableById(long id);

    Patient enableById(long id);

    Patient disableByEmail(String id);

    Patient enableByEmail(String id);


    String forgotPassword(String email);

    String resetPassword(String token, String password);

    List<Patient> findPatientByFlag(Integer id, Boolean aBoolean);

    List<Patient> findPatientByEmailAndFlag(String id, Boolean aBoolean);


}
