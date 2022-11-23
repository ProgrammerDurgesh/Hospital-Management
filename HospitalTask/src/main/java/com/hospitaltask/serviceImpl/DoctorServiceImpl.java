package com.hospitaltask.serviceImpl;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;
import com.hospitaltask.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepo doctorRepo;


    @Override
    public
    Doctor addDoctor(Doctor doctor) {
        return this.doctorRepo.save( doctor );
    }

    @Override
    public
    Doctor updateDoctor(Doctor createDoctor,Long id) {
        return null;
    }

    @Override
    public List< Doctor > getAllDoctor() {
        return (List< Doctor >)doctorRepo.findAll();
    }

    @Override
    public
    Doctor getDoctorById(Long id) {
        return this.doctorRepo.findById(id).get();
    }

    @Override
    public void deleteDoctorById(Long id)
    {
        this.doctorRepo.deleteById(id);
    }

    @Override
    public
    Doctor findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }
}
