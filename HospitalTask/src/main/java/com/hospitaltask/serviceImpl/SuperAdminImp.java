package com.hospitaltask.serviceImpl;

import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminImp implements SuperAdminService {

    @Autowired
    private SuperAdminRepo superAdminRepo;
    @Override
    public com.hospitaltask.entity.SuperAdmin save(com.hospitaltask.entity.SuperAdmin superAdmin) {
        return null;
    }

    @Override
    public com.hospitaltask.entity.SuperAdmin Update(com.hospitaltask.entity.SuperAdmin superAdmin, Long id) {
        return null;
    }

    @Override
    public com.hospitaltask.entity.SuperAdmin findById(Long id) {
        return null;
    }

    @Override
    public com.hospitaltask.entity.SuperAdmin findByEmail(String email) {
        return null;
    }

    @Override
    public List<com.hospitaltask.entity.SuperAdmin> findAll() {
        return null;
    }

    @Override
    public com.hospitaltask.entity.SuperAdmin deleteById(long id) {
        return null;
    }

    @Override
    public com.hospitaltask.entity.SuperAdmin deleteByEmail(String id) {
        return null;
    }
}
