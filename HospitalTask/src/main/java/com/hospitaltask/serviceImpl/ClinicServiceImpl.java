package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.*;
import com.hospitaltask.repository.*;
import com.hospitaltask.service.*;
import com.hospitaltask.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private
    ClinicRepo clinicRepo;

   

    //save & Update operation
    @Override
    public
    Clinic saveClinic ( Clinic clinic )
        {
            return clinicRepo.save ( clinic );
        }

    @Override
    public
    Clinic updateClinicById ( Clinic clinic , Long id )
        {
            Clinic clinic1=clinicRepo.findById ( id ).get ();

            if(Objects.nonNull(clinic.getClinicName ()) && !"".equalsIgnoreCase(clinic.getClinicName ()) )
            {
                clinic1.setClinicName ( clinic.getClinicName () );
            }
            if(Objects.nonNull(clinic.getClinicAddress ()) && !"".equalsIgnoreCase(clinic.getClinicAddress ()) )
            {
                clinic1.setClinicState ( clinic.getClinicAddress () );
            }
            if(Objects.nonNull(clinic.getClinicState ()) && !"".equalsIgnoreCase(clinic.getClinicState ()) )
            {
                clinic1.setClinicState ( clinic.getClinicState () );
            }

            return clinicRepo.save ( clinic1 );
        }



    @Override
    public
    Clinic updateClinicByName ( Clinic clinic , String name )
        {
            Clinic clinic1=clinicRepo.findByClinicName (name);

            if(Objects.nonNull(clinic.getClinicName ()) && !"".equalsIgnoreCase(clinic.getClinicName ()) )
            {
                clinic1.setClinicName ( clinic.getClinicName () );
            }
            if(Objects.nonNull(clinic.getClinicAddress ()) && !"".equalsIgnoreCase(clinic.getClinicAddress ()) )
            {
                clinic1.setClinicState ( clinic.getClinicAddress () );
            }
            if(Objects.nonNull(clinic.getClinicState ()) && !"".equalsIgnoreCase(clinic.getClinicState ()) )
            {
                clinic1.setClinicState ( clinic.getClinicState () );
            }

            return clinicRepo.save ( clinic1 );
        }


    // fetch & filter Operation
    @Override
    public
    Clinic getClinicById ( Long id )
        {
            return clinicRepo.findById ( id ).get ();
        }

    @Override
    public
    List < Clinic > getAllClinic ( )
        {
            return clinicRepo.findAll ();
        }

    @Override
    public
    Clinic findByClinicName(String clinicName)
        {
            return clinicRepo.findByClinicName ( clinicName );
        }


        //Delete operations


    @Override
    public
    void deleteAllClinic ( )
        {
            clinicRepo.deleteAll ();
        }

    @Override
    public
    void deleteClinicById ( Long id )
        {
            clinicRepo.findById ( id );

        }
}
