package com.hospitaltask.repository;

import com.hospitaltask.entity.OtpVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OtpVerify, Integer> {
    @Query(value = "select * from tbl_otp order by id desc limit 1", nativeQuery = true)
    OtpVerify getLastInsertedValue();
}
