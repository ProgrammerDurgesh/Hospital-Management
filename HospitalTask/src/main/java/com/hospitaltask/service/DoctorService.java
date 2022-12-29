package com.hospitaltask.service;

import java.util.List;

import com.hospitaltask.entity.Doctor;

public interface DoctorService {



    //Add & Update Operation

	Doctor addDoctor(Doctor doctor);
    Doctor updateDoctorById(Doctor createDoctor,Long id);
    Doctor updateDoctorByEmail(Doctor createDoctor,String email);
    Doctor updateDoctorByName(Doctor createDoctor,String name);

    String loadUserByUsername(String s);


    //fetch & filter Operation

     List< Doctor > getAllDoctor() ;
     Doctor getDoctorById(Long id);
     Doctor findByEmail(String email);
    List<Doctor> findByDoctorName(String doctorName);


     //Delete Operation

     void deleteAllDoctor();
     void deleteDoctorById(Long id);
     void deleteByDoctorName(String name);
     void deleteByDoctorEmail(String email);

     //External methods


    String getPasswordByEmail( String email);
    String getUsername(String username);
}
