package com.hospitaltask.repository;

import com.hospitaltask.entity.SuperAdmin;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
    String deleteByEmail(String id);
//TODO....
    @Query(value = " delete from tbl_super_user where email=:email",nativeQuery = true)
    SuperAdmin findByEmail(String email);
}
