package com.hospitaltask.repository;

import com.hospitaltask.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository< Roles,Long > {

}
