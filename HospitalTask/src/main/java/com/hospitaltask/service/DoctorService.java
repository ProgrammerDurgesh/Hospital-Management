package com.hospitaltask.service;

import com.hospitaltask.entity.*;

import java.util.List;

public interface DoctorService {


    Doctor addDoctor(Doctor doctor);
     Doctor updateDoctor(Doctor createDoctor,Long id);
      List< Doctor > getAllDoctor() ;
     Doctor getDoctorById(Long id);
     void deleteDoctorById(Long id);
     Doctor findByEmail(String email);

     Doctor findByDoctorName(String name);
}
