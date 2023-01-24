package com.hospitaltask.repository;

import com.hospitaltask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(value = "select * from tbl_patient d where d.id =:id and d.flag=:aBoolean",nativeQuery = true)
    List<Patient> findPatientByFlag(Integer id, Boolean aBoolean);

    @Query(value = "select * from tbl_patient d where d.patient_email =:id and d.flag=:aBoolean",nativeQuery = true)
    List<Patient> findPatientByEmailAndFlag(String id, Boolean aBoolean);

}
