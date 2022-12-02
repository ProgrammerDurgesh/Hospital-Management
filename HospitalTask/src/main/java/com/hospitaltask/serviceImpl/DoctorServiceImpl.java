package com.hospitaltask.serviceImpl;
import com.hospitaltask.repository.ClinicRepo;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;
import com.hospitaltask.entity.*;
import com.hospitaltask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ClinicRepo
             clinicRepo;

    @Autowired
    private
    RoleService roleService;

    //Add & Update Operation
    @Override
    public
    Doctor addDoctor(Doctor doctor) {
        return this.doctorRepo.save( doctor );
    }



    @Override
    public
    Doctor updateDoctorById ( Doctor createDoctor , Long id )
        {
            return null;
        }

    @Override
    public
    Doctor updateDoctorByEmail ( Doctor createDoctor , String email )
        {
            return null;
        }

    @Override
    public
    Doctor updateDoctorByName ( Doctor createDoctor , String name )
        {
            return null;
        }

    //fetch & filter Operation
    @Override
    public List< Doctor > getAllDoctor()
        {
            List<Doctor> doctors= doctorRepo.findAll();
        return doctors;
    }

    @Override
    public
    Doctor getDoctorById(Long id) {
        return this.doctorRepo.findById(id).get();
    }

    @Override
    public
    Doctor findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

    @Override
    public
    Doctor findByDoctorName(String doctorName)
        {
            return doctorRepo.findByDoctorName ( doctorName );
        }

        //Delete Operation
    @Override
    public
    void deleteAllDoctor ( )
        {
            doctorRepo.deleteAll ();
        }
    @Override
    public void deleteDoctorById(Long id)
        {
            this.doctorRepo.deleteById(id);
        }
}
