package com.hospitaltask.service;

import com.hospitaltask.entity.UpdatePasswordByLink;

public interface UpdatePasswordByLinkService {
    public void save(UpdatePasswordByLink updatePasswordByLinkRepo);
    public void delete();
    public void deleteById(long id);
    public void DeleteAll();
    public void findById(long Id);
    public void findAll();

}
