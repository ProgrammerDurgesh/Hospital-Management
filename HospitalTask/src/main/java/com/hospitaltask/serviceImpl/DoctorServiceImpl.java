package com.hospitaltask.serviceImpl;

import java.util.List;

import com.hospitaltask.entity.SuperAdmin;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.hospitaltask.dto.DoctorDto;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    Doctor doctor = null;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    Doctor dtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = this.modelMapper.map(doctorDto, Doctor.class);
        return doctor;
    }

    // Add & Update Operation
    @Override
    public Doctor addDoctor(@NotNull Doctor dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return this.doctorRepo.save(dto);
    }

    @Override
    public Doctor updateDoctorById(Doctor createDoctor, Long id) {
        Doctor update = null;
        Doctor doctor = doctorRepo.findById(id).orElse(null);
        if (doctor != null) {
//			update = updateMethod(doctor, createDoctor);
            doctor.setDoctorName(createDoctor.getDoctorName());
            doctor.setEmail(createDoctor.getEmail());
            doctor.setClinic(createDoctor.getClinic());
            doctor.setAddress(createDoctor.getAddress());
            doctor.setExperience(createDoctor.getExperience());
            doctor.setSpecialization(createDoctor.getSpecialization());
            doctor.setRoles(createDoctor.getRoles());
            doctor.setPassword(passwordEncoder.encode(createDoctor.getPassword()));
            return doctorRepo.save(doctor);
        }
        return update;
    }

    @Override
    public Doctor updateDoctorByEmail(Doctor createDoctor, String email) {
        Doctor update = null;
        Doctor doctor = doctorRepo.findByEmail(email);
        if (!doctor.equals(null)) {
            update = updateMethod(createDoctor);
        }
        return update;
    }

    @Override
    public Doctor updateDoctorByName(Doctor createDoctor, String name) {
        Doctor updateMethod = null;
        Doctor doctor = doctorRepo.findByEmail(name);
        if (!doctor.equals(null)) {
            updateMethod = updateMethod(createDoctor);
        }
        return updateMethod;
    }

    @Override
    public String loadUserByUsername(String s) {
        return null;
    }

    // fetch & filter Operation
    @Override
    public List<Doctor> getAllDoctor() {
        List<Doctor> doctors = doctorRepo.findAll();
        return doctors;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return this.doctorRepo.findById(id).orElse(null);
    }

    @Override
    public Doctor findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

    @Override
    public List<Doctor> findByDoctorName(String doctorName) {
        return doctorRepo.findByDoctorName(doctorName);
    }

    // Delete Operation
    @Override
    public void deleteAllDoctor() {
        doctorRepo.deleteAll();
    }

    @Override
    public void deleteDoctorById(Long id) {
        this.doctorRepo.deleteById(id);
    }

    @Override
    public String getPasswordByEmail(String email) {
        return doctorRepo.getPasswordByEmail(email);
    }

    @Override
    public String getUsername(String username) {
        return doctorRepo.getUsername(username);
    }

    @Override
    public void deleteByDoctorName(String name) {
        Doctor doctor = doctorRepo.findByName(name);
        if (!doctor.equals(null)) doctorRepo.deleteById(doctor.getDoctorId());
    }

    @Override
    public void deleteByDoctorEmail(String email) {
        Doctor doctor = doctorRepo.findByEmail(email);
        if (!doctor.equals(null)) doctorRepo.deleteById(doctor.getDoctorId());
    }

    @Override
    public Doctor disableById(long id) {
        Doctor Doctor = disable(id, null);
        return Doctor;
    }

    @Override
    public Doctor enableById(long id) {
        Doctor doctor1 = enable(id, null);
        return doctor1;
    }

    //TODO ....Delete By Email.....
    @Override
    public Doctor disableByEmail(String id) {
        Doctor doctor1 = disable(0, id);
        return doctor1;
    }

    @Override
    public Doctor enableByEmail(String id) {
        Doctor doctor1 = enable(0, id);
        return doctor1;
    }

    @Override
    public List<Doctor> findDoctorByFlag(Integer id, Boolean aBoolean) {
        return doctorRepo.findDoctorByFlag(id, aBoolean);
    }


    //Update repeated value in one method
    public Doctor updateMethod(@NotNull Doctor doctor) {

        Doctor doctorSave = new Doctor();
        doctorSave.setDoctorName(doctor.getDoctorName());
        doctorSave.setEmail(doctor.getEmail());
        doctorSave.setClinic(doctor.getClinic());
        doctorSave.setAddress(doctor.getAddress());
        doctorSave.setExperience(doctor.getExperience());
        doctorSave.setSpecialization(doctor.getSpecialization());
        doctorSave.setRoles(doctor.getRoles());
        doctorSave.setPassword(passwordEncoder.encode(doctor.getPassword()));
        Doctor save = doctorRepo.save(doctorSave);
        return save;
    }

    public Doctor enable(long idL, String id) {
        if (idL > 0 && id == null) doctor = getDoctorById(idL);
        else doctor = doctorRepo.findByEmail(id);
        doctor.setFlag(true);
        doctorRepo.save(doctor);
        return doctor;
    }

    public Doctor disable(long idL, String id) {
        if (idL > 0 && id == null) doctor = getDoctorById(idL);
        else doctor = doctorRepo.findByEmail(id);
        doctor.setFlag(false);
        doctorRepo.save(doctor);
        return doctor;
    }


}
