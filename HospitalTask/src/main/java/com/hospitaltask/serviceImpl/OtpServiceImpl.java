package com.hospitaltask.serviceImpl;

import java.io.File;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.OtpVerify;
import com.hospitaltask.entity.Patient;
import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.OtpRepo;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpRepo otpRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private SuperAdminRepo adminRepo;

	@Autowired
	private PatientEntityRepo entityRepo;

	private String emailForget;

	@Override
	public void save(OtpVerify otpVerify) {
		OtpVerify save = otpRepo.save(otpVerify);
	}

	@Override
	public Boolean Otp(String subject, String to, String from) {
		String generateOtp = generateEmailOTP(to);
		String message = "One Time Password :  " + generateOtp + "\n" + "This Otp Expire with in 5 mint ";
		if (generateOtp != null) {
			// sendEmail(message, subject, to, from);
			sendAttached(message, subject, to, from);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String generateEmailOTP(String email) {
		String otp = null;
		try {
			otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
			final LocalDateTime creationDate = LocalDateTime.now();
			OtpVerify otpVerify = new OtpVerify();
			otpVerify.setOtp(otp);
			otpVerify.setOtpDateTime(creationDate);
			save(otpVerify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otp;
	}

	@Override
	public Boolean match(com.hospitaltask.entity.Otp otp) {
		Boolean value = null;
		try {
			final long OtpConstant = 5;
			OtpVerify otpVerify = otpRepo.getLastInsertedValue();
			LocalDateTime getTimeFromDb = otpVerify.getOtpDateTime();
			Duration duration = Duration.between(getTimeFromDb, LocalDateTime.now());
			if (duration.toMinutes() > OtpConstant) {
				// return OtpConstant.OTP_EXPIRED;
				value = false;
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP Expired");
			} else {
				value = true;
				try {
					otpRepo.deleteById(otpVerify.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	private void sendAttached(String message, String subject, String to, String from) {
		try {
			// variable for gmail host
			String host = "smtp.gmail.com";
			// get system properties
			Properties properties = System.getProperties();
			System.out.println("this is properties of systems :     " + properties);
			/*
			 * set important information in properties object host set
			 */
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");
			// to get the session object & Authenticate sender I'd & Passcode
			Session instance = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, "gqqttubrbjsynvtc");

				}
			});
			instance.setDebug(true);
			// step 2 compose the message[text,attachment,multimedia];
			MimeMessage mimeMessage = new MimeMessage(instance);
			try {
				// set message from
				mimeMessage.setFrom(from);
				// add recipients
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				// adding subject to message
				mimeMessage.setSubject(subject);
				// adding attachment to message
				String path = "/home/durgesh/Desktop/sigin.html";
				mimeMessage.setText(message);
				MimeBodyPart text = new MimeBodyPart();
				MimeBodyPart file = new MimeBodyPart();
				MimeMultipart mimeMultipart = new MimeMultipart();
				try {
					text.setText(message);
					File file1 = new File(path);
					file.attachFile(file1);
					mimeMultipart.addBodyPart(text);
					mimeMultipart.addBodyPart(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mimeMessage.setContent(mimeMultipart);
				// step 3 : send the message from transport class
				Transport.send(mimeMessage);
				System.out.println("Message Send success.......................");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String passwordUpdate(String password, String email) {
		SuperAdmin admin = adminRepo.findByEmail(email);

		if (admin != null) {
			admin.setPassword(passwordEncoder.encode(password));
			adminRepo.save(admin);
		} else {
			Doctor doctor = doctorRepo.findByEmail(email);
			if (doctor != null) {
				doctor.setPassword(passwordEncoder.encode(password));
				doctorRepo.save(doctor);
			} else {
				Patient patient = entityRepo.findByEmail(email);
				if (patient != null) {
					patient.setPassword(passwordEncoder.encode(password));
					entityRepo.save(patient);
				}
			}
		}
		return "Password Update !!";

	}
}
