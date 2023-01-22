package com.hospitaltask.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospitaltask.entity.Clinic;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.ClinicRepo;
import com.hospitaltask.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepo clinicRepo;

    // save & Update operation
    @Override
    public Clinic saveClinic(Clinic clinic) {
        return clinicRepo.save(clinic);
    }

    @Override
    public Clinic updateClinicById(@NotNull Clinic clinic, Long id) {
        Clinic clinic1 = clinicRepo.findById(id).get();

        if (Objects.nonNull(clinic.getClinicName()) && !"".equalsIgnoreCase(clinic.getClinicName())) {
            clinic1.setClinicName(clinic.getClinicName());
        }
        if (Objects.nonNull(clinic.getClinicAddress()) && !"".equalsIgnoreCase(clinic.getClinicAddress())) {
            clinic1.setClinicState(clinic.getClinicAddress());
        }
        if (Objects.nonNull(clinic.getClinicState()) && !"".equalsIgnoreCase(clinic.getClinicState())) {
            clinic1.setClinicState(clinic.getClinicState());
        }

        return clinicRepo.save(clinic1);
    }

    @Override
    public Clinic updateClinicByName(@NotNull Clinic clinic, String name) {
        Clinic clinic1 = clinicRepo.findByClinicName(name);

        if (Objects.nonNull(clinic.getClinicName()) && !"".equalsIgnoreCase(clinic.getClinicName())) {
            clinic1.setClinicName(clinic.getClinicName());
        }
        if (Objects.nonNull(clinic.getClinicAddress()) && !"".equalsIgnoreCase(clinic.getClinicAddress())) {
            clinic1.setClinicState(clinic.getClinicAddress());
        }
        if (Objects.nonNull(clinic.getClinicState()) && !"".equalsIgnoreCase(clinic.getClinicState())) {
            clinic1.setClinicState(clinic.getClinicState());
        }

        return clinicRepo.save(clinic1);
    }

    // fetch & filter Operation
    @Override
    public Clinic getClinicById(Long id) throws UserNotFoundException {
        Clinic clinic = clinicRepo.findById(id).orElse(null);
        if (!clinic.equals(null)) return clinic;
        else ;
        return clinic;
    }

    @Override
    public List<Clinic> getAllClinic() {
        return clinicRepo.findAll();
    }

    @Override
    public Clinic findByClinicName(String clinicName) {
        return clinicRepo.findByClinicName(clinicName);
    }

    // Delete operations

    @Override
    public void deleteAllClinic() {
        clinicRepo.deleteAll();
    }

    @Override
    public void deleteClinicById(Long id) {
        clinicRepo.findById(id);
        this.clinicRepo.deleteById(id);
    }
}
