package com.hospitaltask.entity;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private final boolean flag=true;
    @Column(name="state",nullable = true, unique = false, length = 45)
    private String clinicState;

    @Column(name="clinic_created")
    private final  Date createdDate = Calendar.getInstance().getTime();


}
