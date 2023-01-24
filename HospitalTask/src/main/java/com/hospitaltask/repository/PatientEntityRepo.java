package com.hospitaltask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hospitaltask.entity.Patient;

@Repository
public interface PatientEntityRepo extends JpaRepository<Patient, Long> {

    @Query(value = "select * from tbl_patient p where p.patient_email=:email", nativeQuery = true)
    Patient findByEmail(String email);

    @Query(value = "select * from tbl_patient p where p.doctor_id=:Id", nativeQuery = true)
    List<Patient> findAllPatientByDoctorId(Long Id);

    Patient findByDoctor(Long id);

    List<Patient> findByName(String name);

    @Query(value = "select * from tbl_patient p where p.flag=:flag", nativeQuery = true)
    List<Patient> findAllByFlag(boolean flag);

}
