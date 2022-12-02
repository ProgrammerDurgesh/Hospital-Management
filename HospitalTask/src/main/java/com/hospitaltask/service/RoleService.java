package com.hospitaltask.service;

import com.hospitaltask.entity.Roles;

import java.util.List;

public interface RoleService {
    //Add & Update Operations

    Roles addRoles(Roles roles);
    Roles updateRoles(Roles roles,Long id);

    //fetch & filter Roles

    List<Roles> getAllRoles();

    Roles getRolesByID(Long id);

    Roles getOne(Long id);


    //Remove  & delete Operations

    public void removeAll();

    public void removeById(Long id);





}
