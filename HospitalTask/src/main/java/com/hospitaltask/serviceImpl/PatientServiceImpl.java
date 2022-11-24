package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.Patient;
import com.hospitaltask.repository.PatientEntityRepo;
import com.hospitaltask.service.DoctorService;
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

    @Autowired
    private DoctorService doctorService;

    @Override
    public
    Patient addNewPatient (Patient patient){
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
    Patient findByName(String email)
        {
            return patientEntityRepo.findByName ( email );
        }

    @Override
    public
    Patient findByByDoctor(Long id)
        {
            return null;
        }


//    @Override
//    public
//    Patient findByByDoctor(Long id)
//        {
//            return patientEntityRepo.findByByDoctor ( id );
//        }

    @Override
    public
    Patient findByEmail(String email)
        {
            return patientEntityRepo.findByEmail ( email );
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
            return patientEntityRepo.findAllByDoctor (doctorService.getDoctorById (id));
        }


                /*    @Override
//    public Patient getPatientByDoctorId(Long doctorId) {
//        return this.patientEntityRepo.findByByDoctorId(doctorId);
//    }

//    @Override
//    public Patient getPatientByEmailId(String email) {
//        return patientEntityRepo.findByEmailId(email);
//    }

//    @Override
//    public Patient getPatientByClinicId(String clinicID) {
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
