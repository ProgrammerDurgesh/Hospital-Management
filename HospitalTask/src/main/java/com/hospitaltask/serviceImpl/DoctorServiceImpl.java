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
    public DoctorEntity addDoctor(DoctorEntity doctorEntity) {
        return this.doctorRepo.save(doctorEntity);
    }

    @Override
    public DoctorEntity updateDoctor(DoctorEntity createDoctor, Long id) {
        return null;
    }

    @Override
    public List<DoctorEntity> getAllDoctor() {
        return (List<DoctorEntity>)doctorRepo.findAll();
    }

    @Override
    public DoctorEntity getDoctorById(Long id) {
        return this.doctorRepo.findById(id).get();
    }

    @Override
    public void deleteDoctorById(Long id)
    {
        this.doctorRepo.deleteById(id);
    }

    @Override
    public DoctorEntity findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }
}
