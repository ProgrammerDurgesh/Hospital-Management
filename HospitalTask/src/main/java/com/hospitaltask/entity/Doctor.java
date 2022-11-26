package com.hospitaltask.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Entity
@Table( name="tbl_doctor" )
public
class Doctor{

    @Id
    @Column(name="doctorId")
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private long doctorId;
    private String
            email;
    private String
            password;
    private String
            doctorName;
    private String
            specialization;
    private String
            experience;

    private String
            address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name="clinic_id" ,nullable=false)
    private Clinic clinic;



    public
    Clinic getClinic()
        {
            return clinic;
        }

    public
    void setClinic(Clinic clinic)
        {
            this.clinic=
                    clinic;
        }


    public
    Doctor()
        {
        }

    public
    Doctor(long doctorId,String email,String password,String doctorName,String specialization,String experience,String address)
        {
            this.doctorId=
                    doctorId;
            this.email=
                    email;
            this.password=
                    password;
            this.doctorName=
                    doctorName;
            this.specialization=
                    specialization;
            this.experience=
                    experience;
            this.address=
                    address;
        }

    public
    long getDoctorId()
        {
            return doctorId;
        }

    public
    void setDoctorId(long doctorId)
        {
            this.doctorId=
                    doctorId;
        }

    public
    String getEmail()
        {
            return email;
        }

    public
    void setEmail(String email)
        {
            this.email=
                    email;
        }

    public
    String getPassword()
        {
            return password;
        }

    public
    void setPassword(String password)
        {
            this.password=
                    password;
        }

    public
    String getDoctorName()
        {
            return doctorName;
        }

    public
    void setDoctorName(String doctorName)
        {
            this.doctorName=
                    doctorName;
        }

    public
    String getSpecialization()
        {
            return specialization;
        }

    public
    void setSpecialization(String specialization)
        {
            this.specialization=
                    specialization;
        }

    public
    String getExperience()
        {
            return experience;
        }

    public
    void setExperience(String experience)
        {
            this.experience=
                    experience;
        }

    public
    String getAddress()
        {
            return address;
        }

    public
    void setAddress(String address)
        {
            this.address=
                    address;
        }


}
