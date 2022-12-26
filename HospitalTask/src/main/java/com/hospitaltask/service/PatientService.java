package com.hospitaltask.service;

import com.hospitaltask.entity.*;

import java.util.List;

public interface PatientService
{
    //Add & Update Operation

    Patient addNewPatient(Patient patient);
    Patient updatePatientById(Patient patient,int clinicId);
    Patient updatePatientByName(Patient patient,String clinicName);





    //Fetch & filter Operation

    List< Patient > getAllPatient();
    Patient getPatientById(Long id);
    Patient findByEmail(String email);
    List< Patient > findByDoctorID(Long id);
    Patient findByName(String name);

    //Delete Operation

    void deleteAllPatient();
    void deletePatientByID(Long patientId);


}
