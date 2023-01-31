package com.hospitaltask.controller;

import com.hospitaltask.entity.Doctor;
import com.hospitaltask.exception.UserNotFoundException;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.DoctorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")

public class DoctorController {
    private final String RECORD_NOT_FOUND = "record Not Found";
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private DoctorService doctorService;

    // Add Doctor

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> addDoctor(@RequestBody @NotNull Doctor doctor) {
        String email = doctorRepo.getEmailByEmai(doctor.getEmail());
        if (email != null)
            return CustomResponseHandler.response("Email already exist", HttpStatus.OK, doctor.getEmail());
        return CustomResponseHandler.response("Create successfully", HttpStatus.CREATED, this.doctorService.addDoctor(doctor));
    }

    // Update Doctor By DoctorName/DoctorEmail/DoctorID

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/save/Id/{id}")
    public ResponseEntity<?> updateById(@RequestBody Doctor doctor, @PathVariable Long id) {
        Doctor doctorCheck = doctorService.updateDoctorById(doctor, id);
        if (doctorCheck != null) {
            return CustomResponseHandler.response("Record Updated", HttpStatus.OK, id);
        } else return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, id);
    }

    //todo ......under Working ......
    /*@PutMapping("/save-email/{email}")
    public ResponseEntity<?> updateByEmail(@RequestBody Doctor doctor, @PathVariable String email) {
        Doctor doctorCheck = doctorRepo.findByEmail(email);
        if (!doctorCheck.equals(null)) {
            doctorService.updateDoctorByEmail(doctor, email);
            return new ResponseEntity<>("Doctor Update By :  " + email + doctor.getEmail(), HttpStatus.OK);
        } else
            return new ResponseEntity<>("Doctor Not Available This Email :" + email, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/save-name/{name}")
    public ResponseEntity<?> updateByName(@RequestBody Doctor doctor, @PathVariable String name) {
        Doctor doctorCheck = doctorRepo.findByName(name);
        if (!doctorCheck.equals(null)) {
            doctorService.updateDoctorByEmail(doctor, name);
            return new ResponseEntity<>("Doctor Update By :  " + name + doctor.getEmail(), HttpStatus.OK);
        } else
            return new ResponseEntity<>("Doctor Not Available This Email :" + name, HttpStatus.NOT_FOUND);
         }
    */

    // fetch All Doctor
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_PATIENT')")
    @GetMapping("/All")
    public ResponseEntity<?> getAllDoctor(boolean flag) {
        List<Doctor> allDoctor = this.doctorService.getAllDoctor(flag);
        if (allDoctor != null) return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, allDoctor);
        return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, null);
    }

    // fetch Doctor By DoctorById

    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get-id/{id}")
    public ResponseEntity<?> getDoctorByID(@PathVariable Long id) {
        Doctor doctor = null;
        try {
            doctor = this.doctorRepo.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (doctor == null) return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, id);
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, this.doctorService.getDoctorById(id));
    }

    //fetch Doctor By DoctorEmailID

    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("get-email/{id}")
    public ResponseEntity<?> findByEmailId(@PathVariable String id) {
        System.out.println(id);
        Doctor doctor = this.doctorRepo.getDoctorByEmail(id);
        if (doctor == null) return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, id);
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, this.doctorService.findByEmail(id));
    }

    // fetch Doctor By DoctorByName
    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("get/{doctorName}")
    public ResponseEntity<?> findByDoctorName(@PathVariable String doctorName) {
        List<Doctor> doctor = this.doctorRepo.findByDoctorName(doctorName);
        if (doctor.size() == 0)
            return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, doctorName);
        return CustomResponseHandler.response("Record Found Success", HttpStatus.OK, doctor);
    }

    //Delete Operation Delete All Doctor
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("Delete-All")
    public ResponseEntity<?> deleteAllDoctor() {
        doctorService.deleteAllDoctor();
        List<Doctor> doctors = doctorRepo.findAll();
        if (doctors != null)
            return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.EXPECTATION_FAILED, doctors);
        return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, doctors);
    }

    // Delete Doctor By DoctorId
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("Delete/{id}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable Long id) throws UserNotFoundException {
        Doctor doctor = doctorRepo.findById(id).orElse(null);
        if (doctor == null) return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, id);
        else {
            doctorService.deleteDoctorById(id);
            return CustomResponseHandler.response("Record Deleted", HttpStatus.OK, id);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findById/{id}/{aBoolean}")
    public ResponseEntity<?> findDoctorByFlag(@PathVariable Integer id, @PathVariable Boolean aBoolean) {
        List<Doctor> doctors = doctorService.findDoctorByFlag(id, aBoolean);
        if (doctors.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, doctors);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findByEmail/{id}/{aBoolean}")
    public ResponseEntity<?> findDoctorByEmailAndFlag(@PathVariable String id, @PathVariable Boolean aBoolean) {
        List<Doctor> doctors = doctorService.findDoctorByEmailAndFlag(id,aBoolean);
        if (doctors.size() == 0) {
            return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id + " " + aBoolean);
        } else
            return CustomResponseHandler.response("Record Found", HttpStatus.OK, doctors);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("Delete/name/{name}")
    public ResponseEntity<?> deleteDoctorByName(@PathVariable String name) throws UserNotFoundException {
        Doctor doctor = doctorRepo.findByName(name);
        if (doctor == null) return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND, name);
        else {
            doctorService.deleteDoctorById(doctor.getDoctorId());
            return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, name);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("Delete/email/{id}")
    public ResponseEntity<?> deleteDoctorByEmail(@PathVariable String id) throws UserNotFoundException {
        Doctor doctor = doctorRepo.getDoctorByEmail(id);
        if (doctor == null) return CustomResponseHandler.response(RECORD_NOT_FOUND, HttpStatus.OK, id);
        else {
            doctorService.deleteDoctorById(doctor.getDoctorId());
            return CustomResponseHandler.response("Record Deleted", HttpStatus.OK, id);
        }
    }

    @PutMapping("disableById/{id}")
    public ResponseEntity<?> disableById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            doctorService.disableById(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("enableById/{id}")
    public ResponseEntity<?> enableById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            doctorService.enableById(id);
            return CustomResponseHandler.response("Update Successfully ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("disableByEmail/{id}")
    public ResponseEntity<?> disableByEmail(@PathVariable String id) {
        Doctor doctor = doctorService.findByEmail(id);
        if (doctor != null) {
            doctorService.disableByEmail(id);
            return CustomResponseHandler.response("Record disable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

    @PutMapping("enableByEmail/{id}")
    public ResponseEntity<?> enableByEmail(@PathVariable String id) {
        Doctor doctor = doctorService.findByEmail(id);
        if (doctor != null) {
            doctorService.enableByEmail(id);
            return CustomResponseHandler.response("Record enable ", HttpStatus.OK, id);
        }
        return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, id);
    }

}