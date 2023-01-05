package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.Roles;
import com.hospitaltask.repository.RoleRepo;
import com.hospitaltask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesImpl implements RoleService {

    @Autowired
   private RoleRepo roleRepo;

    @Override
    public Roles save (Roles roles)
        {
            return roleRepo.save(roles);
        }

    @Override
    public
    Roles updateRoles ( Roles roles , Long id )
        {
            return null;
        }

    @Override
    public
    List < Roles > getAllRoles ( )
        {
            return this.roleRepo.findAll ();
        }

    @Override
    public
    Roles getRolesByID ( Long id )
        {
            return roleRepo.findById ( id ).get ();
        }

    @Override
    public
    Roles getOne ( Long id )
        {
            return roleRepo.getReferenceById ( id );
        }

    @Override
    public
    void removeAll ( )
        {
            roleRepo.deleteAll ();
        }

    @Override
    public void removeById ( Long id )
        {
            roleRepo.deleteById ( id );
        }
}
