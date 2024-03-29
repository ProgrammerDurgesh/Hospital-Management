package com.hospitaltask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hospitaltask.entity.Doctor;


public interface DoctorRepo extends JpaRepository<Doctor, Long> {

//    @Query(value = "select * from tbl_doctor d where d.email=:email", nativeQuery = true)
    Doctor findByEmail(String email);

    List<Doctor> findByDoctorName(String doctorName);

    @Query(value = "select password   from tbl_doctor d where d.email =:email", nativeQuery = true)
    String getPasswordByEmail(@Param("email") String email);

    @Query(value = "select email   from tbl_doctor d where d.email =:email", nativeQuery = true)
    String getUsername(@Param("email") String email);

    @Query(value = " select email from tbl_doctor d where d.email=:email", nativeQuery = true)
    String getEmailByEmail(String email);

    @Query(value = "  select * from tbl_doctor d where d.email=:email and d.is_active=true", nativeQuery = true)
    Doctor getDoctorByEmail(String email);
    // *    @Query(value = "  select * from tbl_doctor d where d.doctor_id= :id",nativeQuery = true)
    //   Doctor getDoctorById(Long id);*/

    @Query(value = "  select * from tbl_doctor d where d.doctor_name= :name", nativeQuery = true)
    Doctor findByName(String name);


    @Query(value = "  select * from tbl_doctor d where d.flag= :flag", nativeQuery = true)
    List<Doctor> findByFlag(boolean flag);


    @Query(value = "select * from tbl_doctor d where d.doctor_id =:id and d.flag=:aBoolean",nativeQuery = true)
    List<Doctor> findDoctorByFlag(Integer id,Boolean aBoolean);



    @Query(value = "select * from tbl_doctor d where d.email =:id and d.flag=:aBoolean",nativeQuery = true)
    List<Doctor> findDoctorByEmailAndFlag(String  id,Boolean aBoolean);

    
    @Query(value = "select * from tbl_doctor d where d.email =:email and d.confirmation_token=:token",nativeQuery = true)
    Doctor acountVerify(String email,String token);
    
    
    
    
    
    
}

