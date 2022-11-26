package com.hospitaltask.entity;

import javax.persistence.*;

@Entity
@Table ( name = "tbl_Patient" )
public
class Patient{


    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long
            id;

    @Column ( name = "patientEmail" )
    private String email;


    private String password, name, age, bloodGroup, illness;



    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn( name="doctor_id" )
    private  Doctor doctor;

    public
    Doctor getDoctor()
        {
            return doctor;
        }

    public
    void setDoctor(Doctor doctor)
        {
            this.doctor=
                    doctor;
        }


    public
    Patient( )
        {
        }

    public
    Patient( long id , String email , String password , String name , String age , String bloodGroup , String illness )
        {
            this.id =
                    id;
            this.email =
                    email;
            this.password =
                    password;
            this.name =
                    name;
            this.age =
                    age;
            this.bloodGroup =
                    bloodGroup;
            this.illness =
                    illness;

        }

    public
    long getId ( )
        {
            return id;
        }

    public
    void setId ( long id )
        {
            this.id =
                    id;
        }

    public
    String getEmail ( )
        {
            return email;
        }

    public
    void setEmail ( String email )
        {
            this.email =
                    email;
        }

    public
    String getPassword ( )
        {
            return password;
        }

    public
    void setPassword ( String password )
        {
            this.password =
                    password;
        }

    public
    String getName ( )
        {
            return name;
        }

    public
    void setName ( String name )
        {
            this.name =
                    name;
        }

    public
    String getAge ( )
        {
            return age;
        }

    public
    void setAge ( String age )
        {
            this.age =
                    age;
        }

    public
    String getBloodGroup ( )
        {
            return bloodGroup;
        }

    public
    void setBloodGroup ( String bloodGroup )
        {
            this.bloodGroup =
                    bloodGroup;
        }

    public
    String getIllness ( )
        {
            return illness;
        }

    public
    void setIllness ( String illness )
        {
            this.illness =
                    illness;
        }


}
