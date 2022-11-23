package com.hospitaltask.repository;

import com.hospitaltask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEntityRepo extends JpaRepository< Patient,Long>
{
/*
    Patient findByEmailId(String emil);
    Patient findByClinicId(String id);
    Patient findByByDoctorId(Long id);
    void deleteByEmailId(String email);
 */
}
