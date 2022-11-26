package com.hospitaltask.repository;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientEntityRepo extends JpaRepository< Patient,Long>
{
    Patient findByEmail(String emil);
    Patient findByDoctor(Long id);


    Patient findByName(String name);

}
