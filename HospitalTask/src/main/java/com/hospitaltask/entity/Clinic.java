package com.hospitaltask.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_clinic")
public class Clinic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clinic_Id")
    private  Long id;
    @Column(nullable = false, unique = true, length = 45)
    private  String  clinicName;
    private String  clinicAddress;
    private String clinicState;


    public
    Clinic ( )
        {
        }

    public
    Clinic ( Long id , String clinicName , String clinicAddress , String clinicState )
        {
            this.id =
                    id;
            this.clinicName =
                    clinicName;
            this.clinicAddress =
                    clinicAddress;
            this.clinicState =
                    clinicState;
        }

    public
    Long getId ( )
        {
            return id;
        }

    public
    void setId ( Long id )
        {
            this.id =
                    id;
        }

    public
    String getClinicName ( )
        {
            return clinicName;
        }

    public
    void setClinicName ( String clinicName )
        {
            this.clinicName =
                    clinicName;
        }

    public
    String getClinicAddress ( )
        {
            return clinicAddress;
        }

    public
    void setClinicAddress ( String clinicAddress )
        {
            this.clinicAddress =
                    clinicAddress;
        }

    public
    String getClinicState ( )
        {
            return clinicState;
        }

    public
    void setClinicState ( String clinicState )
        {
            this.clinicState =
                    clinicState;
        }
}
