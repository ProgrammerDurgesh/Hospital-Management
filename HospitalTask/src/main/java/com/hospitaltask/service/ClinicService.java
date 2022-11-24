package com.hospitaltask.service;

import com.hospitaltask.entity.Clinic;

import java.util.List;

public interface ClinicService {

    Clinic saveClinic(Clinic clinic);
    Clinic getClinicById(Long id);
    List<Clinic> getAllClinic();

    Clinic findByClinicName(String clinicName);

}
