package com.hospitaltask.controller;

import com.hospitaltask.serviceImpl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospitaltask.entity.*;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorServiceImpl doctorServiceImp;
    @GetMapping("/home")
    public  String home()
    {
        return  "this is home page ";
    }

    @PostMapping("/doctor")
    public ResponseEntity< Doctor > addDoctor(@RequestBody Doctor doctor)
    {
        return new ResponseEntity(this.doctorServiceImp.addDoctor(doctor),HttpStatus.CREATED);
    }


   @GetMapping("/doctor")
   public ResponseEntity<List< Doctor >> getAllDoctor()
    {
        return new ResponseEntity<>(this.doctorServiceImp.getAllDoctor(),HttpStatus.OK);
    }

    @GetMapping("/doctor/findById/{id}")
    public ResponseEntity< Doctor > getDoctorByID(@PathVariable Long id)
    {
        return new ResponseEntity< Doctor >(this.doctorServiceImp.getDoctorById(id),HttpStatus.OK);
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity< Doctor > findByEmailId(@PathVariable String id)
    {
        return new ResponseEntity<>(this.doctorServiceImp.findByEmail(id),HttpStatus.OK);
    }

}
