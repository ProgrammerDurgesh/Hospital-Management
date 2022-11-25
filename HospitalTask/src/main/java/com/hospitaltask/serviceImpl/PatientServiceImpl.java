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

    @Override
    public
    Patient addNewPatient (Patient patient)
        {
            System.out.println ( patient.getEmail () );
            System.out.println ( patient.getPassword ());
            System.out.println ( patient.getName () );
            System.out.println ( patient.getEmail () );
            System.out.println ( patient.getAge () );
            System.out.println ( patient.getBloodGroup () );
            System.out.println ( patient.getIllness () );

            return patientEntityRepo.save ( patient );
        }

    @Override
    public
    Patient updatePatientById (Patient patient,int id )
        {
            return null;
        }

    @Override
    public
    Patient updatePatientByEmailId (Patient patient,String emailId )
        {
            return null;
        }

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
    void deletePatientByID ( Long patientId )
        {

            this.patientEntityRepo.deleteById ( patientId );
        }

    @Override
    public
    void deleteAllPatient ()
        {
            this.patientEntityRepo.deleteAll ( );
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
    public Patient getPatientByDoctorId(Long doctorId) {
        return this.patientEntityRepo.findByByDoctorId(doctorId);
    }

    @Override
    public Patient findByEmail(String email) {
        return patientEntityRepo.findByEmail(email);
    }

    @Override
    public Patient getPatientByClinicId(String clinicID) {
        return null;
    }


    @Override
    public void deletePatientIdByEmailID(String emailId) {

        this.patientEntityRepo.deleteByEmailId(emailId);
    }

    @Override
    public void deletePatientByClinicCode(String clinicCode) {

    }
}
