package com.hospitaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table( name="tbl_doctor" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;



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
        @JoinColumn( name="role_id" ,nullable=false)
    private Roles roles;



}
