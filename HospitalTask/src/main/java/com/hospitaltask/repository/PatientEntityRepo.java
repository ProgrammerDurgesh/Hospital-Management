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

    Patient findByToken(String token);

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
    
    
    @Query(value = "select * from tbl_patient d where d.patient_email =:email and d.confirmation_token=:token",nativeQuery = true)
    Patient acountVerify(String email,String token);
    

}
