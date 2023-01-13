package com.hospitaltask.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name="tbl_doctor" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @Column(name="doctorId")
    @GeneratedValue( strategy=GenerationType.AUTO )
    private long doctorId;
    private String doctorName;
    @Column(unique=true,nullable=false,length=35 )
    private String email;
    private String password;
    private String specialization;
    private String experience;
    private String address;

    @Column(name="doctor_joining_date")
    private final  Date createdDate = Calendar.getInstance().getTime();

    @ManyToOne
    @JoinColumn( name="clinic_id" ,nullable=false)
    private Clinic clinic;


    @ManyToOne
        @JoinColumn( name="role_id" ,nullable=false,updatable =true)
    private Roles roles;



}
