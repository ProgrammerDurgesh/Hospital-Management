package com.hospitaltask.repository;

import com.hospitaltask.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    @Query(value = "select role_name from tbl_roles s where s.role_name=:name", nativeQuery = true)
    String getRoleName(String name);

    @Query(value = "select * from tbl_roles s where s.role_id=:role", nativeQuery = true)
    Roles getRoleById(Long role);

    @Query(value = "select role_name from tbl_roles where role_id=:id", nativeQuery = true)
    String getRoleNameByRoleId(Long id);



}
