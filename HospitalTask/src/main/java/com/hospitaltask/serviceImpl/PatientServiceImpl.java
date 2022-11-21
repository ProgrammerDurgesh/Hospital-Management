package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.PatientEntity;
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
    PatientEntity addNewPatient ( PatientEntity patientEntity )
        {
            return patientEntityRepo.save ( patientEntity );
        }

    @Override
    public
    PatientEntity updatePatientById ( PatientEntity patientEntity , int id )
        {
            return null;
        }

    @Override
    public
    PatientEntity updatePatientByEmailId ( PatientEntity patientEntity , String emailId )
        {
            return null;
        }

    @Override
    public
    List < PatientEntity > getAllPatient ()
        {
            return patientEntityRepo.findAll ( );
        }

    @Override
    public
    PatientEntity getPatientById ( Long id )
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

                /*    @Override
//    public PatientEntity getPatientByDoctorId(Long doctorId) {
//        return this.patientEntityRepo.findByByDoctorId(doctorId);
//    }

//    @Override
//    public PatientEntity getPatientByEmailId(String email) {
//        return patientEntityRepo.findByEmailId(email);
//    }

//    @Override
//    public PatientEntity getPatientByClinicId(String clinicID) {
//        return null;
//    }



//    @Override
//    public void deletePatientIdByEmailID(String emailId) {
//
//        this.patientEntityRepo.deleteByEmailId(emailId);
//    }

//    @Override
//    public void deletePatientByClinicCode(String clinicCode) {
//
//    }
*/
}
