package com.hospitaltask.service;

import com.hospitaltask.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorService {


    DoctorEntity addDoctor(DoctorEntity doctorEntity);
     DoctorEntity updateDoctor(DoctorEntity createDoctor,Long id);
      List<DoctorEntity> getAllDoctor() ;
     DoctorEntity getDoctorById(Long id);
     void deleteDoctorById(Long id);
     DoctorEntity findByEmail(String email);
}
