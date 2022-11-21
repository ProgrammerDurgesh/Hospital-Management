package com.hospitaltask.repository;

import com.hospitaltask.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<DoctorEntity,Long> {

    DoctorEntity findByEmail(String email);
}

