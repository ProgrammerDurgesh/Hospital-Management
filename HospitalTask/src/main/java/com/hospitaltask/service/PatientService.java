package com.hospitaltask.service;

import com.hospitaltask.entity.Patient;

import java.util.List;

public interface PatientService
{
    Patient addNewPatient(Patient patient);

    Patient updatePatientById(Patient patient,int id);

    Patient updatePatientByEmailId(Patient patient,String emailId);

    List< Patient > getAllPatient();

    Patient getPatientById(Long id);

    Patient findByName(String email);
    Patient findByByDoctor(Long id);

//    Patient getPatientByDoctorId(Long doctorId);
//
     Patient findByEmail(String email);
//
//    Patient getPatientByClinicId(String clinicID);

    void deletePatientByID(Long patientId);

//    void deletePatientIdByEmailID(String emailId);
//
//    void deletePatientByClinicCode(String clinicCode);

    void deleteAllPatient();

    List< Patient > findByDoctorID(Long id);


}
