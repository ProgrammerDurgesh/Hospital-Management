package com.hospitaltask.repository;

import com.hospitaltask.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepo extends JpaRepository< Doctor,Long> {

    Doctor findByEmail(String email);
    Doctor findByDoctorName(String doctorName);

    @Query(value="select password   from tbl_doctor d where d.email =:email", nativeQuery=true)
    String getPasswordByEmail(@Param("email") String email);

    @Query(value="select email   from tbl_doctor d where d.email =:email", nativeQuery=true)
    String getUsername(@Param("email") String email);
}

