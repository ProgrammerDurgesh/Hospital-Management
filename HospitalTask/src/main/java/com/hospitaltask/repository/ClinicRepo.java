package com.hospitaltask.repository;

import com.hospitaltask.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepo extends JpaRepository<Clinic, Long> {
    // @Query(value = "select * from tbl_clinic where clinic_name=:id",nativeQuery =true)
    Clinic findByClinicName(String clinicName);

    @Query(value = "select * from tbl_clinic where clinic_id=:id", nativeQuery = true)
    Clinic getClinicById(Long id);


    @Query(value = "select * from tbl_clinic c where c.flag=:aBoolean", nativeQuery = true)
    List<Clinic> getAllEnableClinic(Boolean aBoolean);

}
