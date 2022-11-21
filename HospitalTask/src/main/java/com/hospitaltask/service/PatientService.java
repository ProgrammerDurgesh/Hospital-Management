package com.hospitaltask.service;

import com.hospitaltask.entity.PatientEntity;

import java.util.List;

public interface PatientService
{
    PatientEntity addNewPatient(PatientEntity patientEntity);

    PatientEntity updatePatientById(PatientEntity patientEntity, int id);

    PatientEntity updatePatientByEmailId(PatientEntity patientEntity, String emailId);

    List<PatientEntity> getAllPatient();

    PatientEntity getPatientById(Long id);

//    PatientEntity getPatientByDoctorId(Long doctorId);
//
//    PatientEntity getPatientByEmailId(String email);
//
//    PatientEntity getPatientByClinicId(String clinicID);

    void deletePatientByID(Long patientId);

//    void deletePatientIdByEmailID(String emailId);
//
//    void deletePatientByClinicCode(String clinicCode);

    void deleteAllPatient();


}
