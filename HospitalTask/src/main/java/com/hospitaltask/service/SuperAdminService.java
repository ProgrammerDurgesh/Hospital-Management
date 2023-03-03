package com.hospitaltask.service;

import java.util.List;

import com.hospitaltask.dto.SuperUserDto;
import com.hospitaltask.entity.SuperAdmin;

public interface SuperAdminService {
    SuperAdmin save(SuperUserDto superAdmin);

    SuperAdmin update(SuperAdmin superAdmin, Long id);

    SuperAdmin findById(Long id);

    SuperAdmin findByEmail(String email);

    List<SuperAdmin> findAll();

    SuperAdmin disableById(long id);

    SuperAdmin enableById(long id);

    SuperAdmin disableByEmail(String id);

    SuperAdmin enableByEmail(String id);

    List<SuperAdmin> findSuperUserByFlag(Integer id, Boolean aBoolean);

    List<SuperAdmin> findSuperAdminByEmailAndFlag(String id, Boolean aBoolean);


}
