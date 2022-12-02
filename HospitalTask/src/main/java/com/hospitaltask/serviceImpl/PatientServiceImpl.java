package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class PatientServiceImpl implements PatientService {
    @Autowired
    PatientEntityRepo
            patientEntityRepo;

    //Add & Update Operation
    @Override
    public
    Patient addNewPatient (Patient patient){
        Patient patient1=null;
        try
        {
             patient1=patientEntityRepo.save ( patient );
        }
        catch (Exception e)
        {
            System.out.println ("" );
            e.printStackTrace ();
        }
            return patient1;
        }

    @Override
    public
    Patient updatePatientById (Patient patient,int id )
        {
            return null;
        }

    @Override
    public
    Patient updatePatientByName ( Patient patient , String clinicName )
        {
            return null;
        }


    //fetch & Filter Operation

    @Override
    public
    List < Patient > getAllPatient ()
        {
            return patientEntityRepo.findAll ( );
        }

    @Override
    public
    Patient getPatientById (Long id )
        {
            return patientEntityRepo.findById ( id )
                    .get ( );
        }

    @Override
    public
    List < Patient > findByDoctorID(Long id)
        {
            return (List < Patient >) patientEntityRepo.findById ( id ).get ();
        }

    @Override
    public
    Patient findByName(String name)
        {
            return patientEntityRepo.findByName(name);
        }



    @Override
    public Patient findByEmail(String email) {
        return patientEntityRepo.findByEmail(email);
    }


    //Delete Operation

    @Override
    public
    void deleteAllPatient ()
        {
            this.patientEntityRepo.deleteAll ( );
        }

    @Override
    public
    void deletePatientByID ( Long patientId )
        {

            this.patientEntityRepo.deleteById ( patientId );
        }


}
