package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.Clinic;
import com.hospitaltask.repository.ClinicRepo;
import com.hospitaltask.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private
    ClinicRepo clinicRepo;

    @Override
    public
    Clinic saveClinic ( Clinic clinic )
        {
            return clinicRepo.save ( clinic );
        }

    @Override
    public
    Clinic getClinicById ( Long id )
        {
            return clinicRepo.findById ( id ).get ();
        }

    @Override
    public
    List < Clinic > getAllClinic ( )
        {
            return clinicRepo.findAll ();
        }
}
