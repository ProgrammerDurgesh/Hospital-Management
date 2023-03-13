package com.hospitaltask.serviceImpl;

import java.awt.Paint;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientEntityRepo patientRepo;

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

	Patient patient = null;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SendEmailTemplate emailTemplate;

	// Add & Update Operation
	@Override
	public Patient save(@NotNull Patient patient) {

		Patient save = null;
		Patient email = patientRepo.findByEmail(patient.getEmail());
		if (email == null) {
			patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));

			// email for verification

			String conformationToken = UUID.randomUUID().toString();
			patient.setConfirmationToken(conformationToken);
			String url = "127.0.0.1:8000/patient/verify/" + patient.getEmail() + "/" + conformationToken;

			patient.setIsActive(false);
			String message = "Notification Account Activation";
			String obj = "Please verify your email address to get access to your account   " + "\n" + url + "\n"
					+ "Thank You ";
			emailTemplate.sendAttached(obj, message, patient.getEmail());

			save = patientRepo.save(patient);
			return save;
		}

		return save;
	}

	@Override
	public Patient updatePatientById(Patient patient, Long id) {
		Patient save = null;
		Patient patient2 = patientRepo.findById(id).orElse(null);
		if (patient2 == null) {
			return patient2;
		} else {
			patient2.setName(patient.getName());
			patient2.setEmail(patient.getEmail());
			patient2.setDoctor(patient.getDoctor());
			patient2.setAge(patient.getAge());
			patient2.setBloodGroup(patient.getBloodGroup());
			patient2.setIllness(patient.getIllness());
			patient2.setPassword(patient.getPassword());
			save = patientRepo.save(patient2);
		}

		return save;
	}

	@Override
	public Patient updatePatientByName(Patient patient, String clinicName) {
		return null;
	}

	// fetch & Filter Operation

	@Override
	public List<Patient> getAllPatient(boolean flag) {
		return patientRepo.findAllByFlag(flag);

	}

	public List<Patient> getAllPatient() {
		return patientRepo.findAll();
	}

	public String forgotPassword(String email) {
		Optional<Patient> optional = Optional.ofNullable(patientRepo.findByEmail(email));

		if (!optional.isPresent()) {
			return "Invalid email id:" + email;
		}

		Patient patient = optional.get();
		patient.setToken(generateToken());
		patient.setTokenCreationDate(LocalDateTime.now());

		patient = patientRepo.save(patient);

		return patient.getToken();

	}

	public String resetPassword(String token, String password) {

		Optional<Patient> optional = Optional.ofNullable(patientRepo.findByToken(token));

		if (!optional.isPresent()) {
			return "Invalid token";
		}

		LocalDateTime tokenCreationDate = optional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Token expired";
		}

		Patient patient = optional.get();

		patient.setPassword(bCryptPasswordEncoder.encode(password));
		patient.setToken(null);
		patient.setTokenCreationDate(null);

		System.out.println(patient);
		patientRepo.save(patient);

		return "Your password successfully updated";

	}

	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	@Override
	public Patient getPatientById(Long id) {
		return patientRepo.findById(id).orElse(null);
	}

	@Override
	public Optional<Patient> findByDoctorID(Long id) {
		return patientRepo.findById(id);
	}

	@Override
	public List<Patient> findByName(String name) {
		return patientRepo.findByName(name);
	}

	@Override
	public Patient findByEmail(String email) {
		return patientRepo.findByEmail(email);
	}

	// Delete Operation

	@Override
	public void deleteAllPatient() {
		this.patientRepo.deleteAll();
	}

	@Override
	public void deletePatientByID(Long patientId) {

		this.patientRepo.deleteById(patientId);
	}

	@Override
	public Patient disableById(long id) {
		Patient patient = disable(id, null);
		return patient;
	}

	@Override
	public Patient enableById(long id) {
		Patient patient = enable(id, null);
		return patient;
	}

	// TODO ....Delete By Email.....
	@Override
	public Patient disableByEmail(String id) {
		Patient patient = disable(0, id);
		return patient;
	}

	@Override
	public Patient enableByEmail(String id) {
		Patient patient = enable(0, id);
		return patient;
	}

	@Override
	public List<Patient> findPatientByFlag(Integer id, Boolean aBoolean) {
		return patientRepo.findPatientByFlag(id, aBoolean);
	}

	@Override
	public List<Patient> findPatientByEmailAndFlag(String id, Boolean aBoolean) {
		return patientRepo.findPatientByEmailAndFlag(id, aBoolean);
	}

	public Patient enable(long idL, String id) {
		if (idL > 0 && id == null)
			patient = getPatientById(idL);
		else
			patient = patientRepo.findByEmail(id);
		patient.setFlag(true);
		Patient save = patientRepo.save(patient);
		return save;
	}

	public Patient disable(long idL, String id) {
		if (idL > 0 && id == null)
			patient = getPatientById(idL);
		else
			patient = patientRepo.findByEmail(id);
		patient.setFlag(false);
		Patient save = patientRepo.save(patient);
		return save;
	}

	@Override
	public Patient acountVerify(String email, String token) {

		Patient acountVerify = null;
		try {
			acountVerify = patientRepo.acountVerify(email, token);
			if (token.equals(acountVerify.getConfirmationToken())) {
				acountVerify.setIsActive(true);
				acountVerify.setConfirmationToken(null);
				patientRepo.save(acountVerify);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return acountVerify;
	}

}
