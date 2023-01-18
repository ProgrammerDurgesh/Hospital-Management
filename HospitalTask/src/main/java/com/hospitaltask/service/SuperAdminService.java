package com.hospitaltask.service;

import com.hospitaltask.dto.SuperUserDto;
import com.hospitaltask.entity.SuperAdmin;

import java.util.List;

public interface SuperAdminService {
    SuperAdmin save(SuperUserDto superAdmin);

    SuperAdmin update(SuperAdmin superAdmin, Long id);

    SuperAdmin findById(Long id);

    SuperAdmin findByEmail(String email);

    List<SuperAdmin> findAll();

    String deleteById(long id);

    String deleteByEmail(String id);


}
