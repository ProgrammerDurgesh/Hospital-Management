package com.hospitaltask.repository;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface PatientEntityRepo extends JpaRepository < Patient, Long >{
    Patient findByEmail(String email);

    Patient findByName(String email);

    List < Patient > findAllByDoctor(Doctor doctorList);
/*
    Patient findByEmailId(String emil);
    Patient findByClinicId(String id);
    Patient findByByDoctorId(Long id);
    void deleteByEmailId(String email);
 */
}
