package com.hospitaltask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospitaltask.entity.Patient;

@Repository
public interface PatientEntityRepo extends JpaRepository< Patient,Long>
{

    Patient findByEmail(String email);
    @Query(value = "select name from tbl_patient p where p.doctor_id=:Id",nativeQuery = true )
    List<Patient> findAllPatientByDoctorId(Long Id);
    Patient findByDoctor(Long id);
    Patient findByName(String name);

}
