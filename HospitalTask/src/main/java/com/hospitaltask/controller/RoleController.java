package com.hospitaltask.controller;

import com.hospitaltask.entity.Roles;
import com.hospitaltask.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService
            roleService;

    /*
    add & Update Controller
     */

    @PostMapping ( "/role")
    public ResponseEntity < Roles > addRoles ( @RequestBody Roles roles )
        {
            System.out.println ("this is Add method " );
            return new ResponseEntity <> ( roleService.addRoles ( roles ) , HttpStatus.CREATED );
        }

    /*
    fetch & filter Controller
     */

    @GetMapping ( "/role")
    public
    ResponseEntity < List < Roles > > getAllRoles ( )
        {
            return new ResponseEntity <> ( roleService.getAllRoles ( ) , HttpStatus.OK );
        }

    @GetMapping ( "role/{id}")
    public
    ResponseEntity < Roles > getRoleById ( @PathVariable Long id )
        {
            return new ResponseEntity <> ( roleService.getRolesByID ( id ) , HttpStatus.OK );
        }

        /*
        Remove & Delete Controller
         */

    @DeleteMapping ( "/role")
    public
    void removeAllRoles ( )
        {
            roleService.removeAll ( );
        }

    @DeleteMapping ( "role/{id}")
    public void removeRolesById (@PathVariable Long id )
        {
            roleService.removeById ( id );
        }
}
