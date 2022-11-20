package com.example.hospitaltask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String docatorName;
    private String specialization;
    private String experirence;
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

    public String getDocatorName() {
        return docatorName;
    }

    public void setDocatorName(String docatorName) {
        this.docatorName = docatorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExperirence() {
        return experirence;
    }

    public void setExperirence(String experirence) {
        this.experirence = experirence;
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

    public DoctorEntity(long id, String email, String password, String docatorName, String specialization, String experirence, String clinicName, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.docatorName = docatorName;
        this.specialization = specialization;
        this.experirence = experirence;
        this.clinicName = clinicName;
        this.address = address;
    }
}
