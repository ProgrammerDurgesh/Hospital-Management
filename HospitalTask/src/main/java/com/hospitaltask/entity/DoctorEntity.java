package com.hospitaltask.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String doctorName;
    private String specialization;
    private String experience;
    private String clinicName;
    private String address;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DoctorEntity() {
    }

    public DoctorEntity(long id, String email, String password, String doctorName, String specialization, String experience, String clinicName, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.experience = experience;
        this.clinicName = clinicName;
        this.address = address;
    }
}
