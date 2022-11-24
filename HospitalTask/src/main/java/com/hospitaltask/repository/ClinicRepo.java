package com.hospitaltask.repository;

import com.hospitaltask.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ClinicRepo extends JpaRepository< Clinic,Long > {

    Clinic findByClinicName(String clinicName);

}
