package com.hospitaltask.service;

import java.util.List;

import com.hospitaltask.entity.SuperAdmin;

public interface SuperAdminService {
    SuperAdmin save(SuperAdmin superAdmin);
    SuperAdmin acountVerify(String email, String token);

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
