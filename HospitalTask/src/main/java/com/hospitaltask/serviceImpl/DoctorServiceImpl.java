package com.hospitaltask.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitaltask.dto.DoctorDto;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.service.DoctorService;

@Service
@SuppressWarnings("unused")

public class DoctorServiceImpl implements DoctorService {

	Doctor doctor = null;
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	@Autowired
	private EmailService emailService;
	Doctor dtoToDoctor(DoctorDto doctorDto) {
		return this.modelMapper.map(doctorDto, Doctor.class);
	}

	// Add & Update Operation
	@Override
	public Doctor addDoctor(@NotNull Doctor dto) {

		// Current Logged User Details
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// email for verification

		String conformationToken = UUID.randomUUID().toString();
		dto.setConfirmationToken(conformationToken);
		String url="http://localhost:8000/doctor/verify/"+dto.getEmail()+"/"+conformationToken;
		String text="Activate Your Account "+"\n"+url;
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		dto.setIsActive(false);
		dto.setCreatedBy(userDetails.getUsername());
		Doctor save = doctorRepo.save(dto);
		emailService.sendWithOutHtmlPage(dto.getEmail(),"Account Activation !!",text);
		return save;
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
	public List<Doctor> getAllDoctor(boolean flag) {
		List<Doctor> doctors = doctorRepo.findByFlag(flag);
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
		if (!doctor.equals(null))
			doctorRepo.deleteById(doctor.getDoctorId());
	}

	@Override
	public void deleteByDoctorEmail(String email) {
		Doctor doctor = doctorRepo.findByEmail(email);
		if (!doctor.equals(null))
			doctorRepo.deleteById(doctor.getDoctorId());
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

	// TODO ....Delete By Email.....
	@Override
	public Doctor disableByEmail(String id) {
		Doctor doctor1 = disable(0, id);
		return doctor1;
	}

	@Override
	public Doctor enableByEmail(@NotNull String id) {
		Doctor doctor1 = enable(0, id);
		return doctor1;
	}

	@Override
	public List<Doctor> findDoctorByFlag(@NotNull Integer id, @NotNull Boolean aBoolean) {
		return doctorRepo.findDoctorByFlag(id, aBoolean);
	}

	@Override
	public List<Doctor> findDoctorByEmailAndFlag(@NotNull String id, @NotNull Boolean aBoolean) {
		return doctorRepo.findDoctorByEmailAndFlag(id, aBoolean);
	}

	// Update repeated value in one method
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
		if (idL > 0 && id == null)
			doctor = getDoctorById(idL);
		else
			doctor = doctorRepo.findByEmail(id);
		doctor.setFlag(true);
		doctorRepo.save(doctor);
		return doctor;
	}

	public Doctor disable(long idL, String id) {
		if (idL > 0 && id == null)
			doctor = getDoctorById(idL);
		else
			doctor = doctorRepo.findByEmail(id);
		doctor.setFlag(false);
		doctorRepo.save(doctor);
		return doctor;
	}

	@Override
	public Doctor acountVerify(String email, String token) {
		
		Doctor acountVerify=null;
		try {
			 acountVerify = doctorRepo.acountVerify(email, token);
			 if(token.equals(acountVerify.getConfirmationToken())){
			acountVerify.setIsActive(true);
			acountVerify.setConfirmationToken(null);
			doctorRepo.save(acountVerify);}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return acountVerify;
	}

}
