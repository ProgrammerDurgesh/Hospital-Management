package com.hospitaltask.repository;

import com.hospitaltask.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository< Doctor,Long> {

    Doctor findByEmail(String email);
    Doctor findByDoctorName(String name);
}

