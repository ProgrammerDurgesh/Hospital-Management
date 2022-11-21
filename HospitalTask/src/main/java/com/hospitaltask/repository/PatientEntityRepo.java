package com.hospitaltask.repository;

import com.hospitaltask.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEntityRepo extends JpaRepository<PatientEntity,Long>
{
/*
    PatientEntity findByEmailId(String emil);
    PatientEntity findByClinicId(String id);
    PatientEntity findByByDoctorId(Long id);
    void deleteByEmailId(String email);
 */
}
