package com.hospitaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_clinic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clinic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clinic_Id")
    private  Long id;
    @Column(nullable = false, length = 45)
    private  String  clinicName;
    private String  clinicAddress;
    @Column(name="state",nullable = true, unique = false, length = 45)
    private String clinicState;

    @Column(name="clinic_created")
    private final  Date createdDate = Calendar.getInstance().getTime();


}
