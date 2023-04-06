package com.hospitaltask.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.hospitaltask.entity.*;
import com.hospitaltask.repository.*;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hospitaltask.service.OtpService;

@Service
@SuppressWarnings("unused")
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
    @Autowired
    private EmailService emailService;

    @Autowired
    private UpdatePasswordByLinkRepo updatePasswordByLinkRepo;

    @Override
    public void save(OtpVerify otpVerify) {
        otpRepo.save(otpVerify);
    }


    @SuppressWarnings("null")
    @Override
    public String generateEmailOTP(String email, int selectUserType) {

        String otp = null;
        SuperAdmin superAdmin = null;
        Doctor doctor = null;
        Patient patient = null;
        try {
            if (selectUserType == 1) {
                superAdmin = adminRepo.findByEmail(email);
            }
            if (selectUserType == 2) {
                doctor = doctorRepo.findByEmail(email);
            } else {
                patient = entityRepo.findByEmail(email);
            }
            otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
            final LocalDateTime creationDate = LocalDateTime.now();
            OtpVerify otpVerify = new OtpVerify();
            if (superAdmin != null && doctor == null && patient == null) {
                otpVerify.setOtp(otp);
                otpVerify.setUserId(superAdmin.getId());
                otpVerify.setUserRole(superAdmin.getRoles().getId());
                otpVerify.setOtpDateTime(creationDate);
                save(otpVerify);
            } else if (superAdmin == null && doctor != null && patient == null) {
                otpVerify.setOtp(otp);
                otpVerify.setUserId(superAdmin.getId());
                otpVerify.setUserRole(superAdmin.getRoles().getId());
                otpVerify.setOtpDateTime(creationDate);
                save(otpVerify);
            } else if (superAdmin == null && doctor == null && patient != null) {
                otpVerify.setOtp(otp);
                otpVerify.setUserId(superAdmin.getId());
                otpVerify.setUserRole(superAdmin.getRoles().getId());
                otpVerify.setOtpDateTime(creationDate);
                save(otpVerify);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otp;
    }

    @Override
    public int match(String otp) {
        int value = 0;
        Duration duration = null;
        long otpId = 0;
        try {
            SuperAdmin superAdmin = adminRepo.findByEmail("durgesh.y@drcsystems.com");
            Doctor doctor = doctorRepo.findByEmail("");
            Patient patient = entityRepo.findByEmail("");
            final long OtpConstant = 5;
            List<OtpVerify> otpVerify = otpRepo.findAll();
            for (int i = 0; i < otpVerify.size(); i++) {

                if (otp.equals(otpVerify.get(i).getOtp())) {
                    LocalDateTime getTimeFromDb = otpVerify.get(i).getOtpDateTime();
                    duration = Duration.between(getTimeFromDb, LocalDateTime.now());
                    otpId = otpVerify.get(i).getId();
                }
            }
            if (duration.toMinutes() > OtpConstant) {
                value++;
                // return OtpConstant.OTP_EXPIRED;
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP Expired");
            } else {

                try {
                    otpRepo.deleteById((int) otpId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            value = 0;
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
            admin.setLastModifiedDate(Calendar.getInstance().getTime());
            adminRepo.save(admin);
        } else {
            Doctor doctor = doctorRepo.findByEmail(email);
            if (doctor != null) {
                doctor.setPassword(passwordEncoder.encode(password));
                doctor.setLastModifiedDate(Calendar.getInstance().getTime());
                doctorRepo.save(doctor);
            } else {
                Patient patient = entityRepo.findByEmail(email);
                if (patient != null) {
                    patient.setPassword(passwordEncoder.encode(password));
                    patient.setLastModifiedDate(Calendar.getInstance().getTime());
                    entityRepo.save(patient);
                }
            }
        }
        return "Password Update !!";
    }

    /*public String  updatePassword(String email, String password) {
        SuperAdmin admin = new SuperAdmin();
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        String response = null;

        try {
            admin = adminRepo.findByEmail(email);
            if (admin != null) {
                admin.setPassword(passwordEncoder.encode(password));
                adminRepo.save(admin);
                response = "ok";
                return response;
            } else {
                if (admin == null) {
                    doctor = doctorRepo.findByEmail(email);
                    doctor.setPassword(passwordEncoder.encode(password));
                    doctorRepo.save(doctor);
                    response = "ok";
                    return response;

                } else if (doctor == null) {
                    patient = entityRepo.findByEmail(email);
                    patient.setPassword(passwordEncoder.encode(password));
                    entityRepo.save(patient);
                    response = "ok";
                    return response;
                }
            }
        } catch (Exception e) {
            response=null;
            e.printStackTrace();
        }
    return response;
    }
*/

}
