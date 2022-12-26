package com.hospitaltask.service;

import com.hospitaltask.entity.*;

import java.util.List;

public interface ClinicService {

    //add & Update operation
    Clinic saveClinic(Clinic clinic);

    Clinic updateClinicById(Clinic clinic,Long id);
    Clinic updateClinicByName(Clinic clinic,String name);

    //fetch & filter operation
    Clinic getClinicById(Long id);
    List<Clinic> getAllClinic();
    Clinic findByClinicName(String clinicName);


    // Delete Operation
    void deleteAllClinic();

    void deleteClinicById(Long id);
}
