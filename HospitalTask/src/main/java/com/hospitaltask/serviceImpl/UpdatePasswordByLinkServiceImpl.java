package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.UpdatePasswordByLink;
import com.hospitaltask.repository.UpdatePasswordByLinkRepo;
import com.hospitaltask.service.UpdatePasswordByLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePasswordByLinkServiceImpl implements UpdatePasswordByLinkService {

    @Autowired
    private UpdatePasswordByLinkRepo updatePasswordByLinkRepo;
    @Override
    public void save(UpdatePasswordByLink updatePasswordByLink) {
        updatePasswordByLinkRepo.save(updatePasswordByLink);
    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void DeleteAll() {

    }

    @Override
    public void findById(long Id) {

    }

    @Override
    public void findAll() {

    }
}
