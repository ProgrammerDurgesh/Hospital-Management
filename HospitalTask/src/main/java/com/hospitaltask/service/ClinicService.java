package com.hospitaltask.service;

import com.hospitaltask.entity.*;
import com.hospitaltask.exception.UserNotFoundException;

import java.util.List;

public interface ClinicService {

    //add & Update operation
    Clinic saveClinic(Clinic clinic);

    Clinic updateClinicById(Clinic clinic, Long id);

    Clinic updateClinicByName(Clinic clinic, String name);

    //fetch & filter operation
    Clinic getClinicById(Long id) throws UserNotFoundException;

    List<Clinic> getAllClinic();

    Clinic findByClinicName(String clinicName);

    List<Clinic> getAllClinic(Boolean aBoolean);


    // Delete Operation
    void deleteAllClinic();

    void deleteClinicById(Long id);
}
