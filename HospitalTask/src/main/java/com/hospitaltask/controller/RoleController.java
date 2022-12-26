package com.hospitaltask.controller;

import com.hospitaltask.entity.*;
import com.hospitaltask.repository.RoleRepo;
import com.hospitaltask.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/HM/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepo roleRepo;

    /*
    add & Update Controller
     */

    @PostMapping ( "/save")
    public ResponseEntity < ? > addRoles ( @RequestBody Roles roles )
        {
            String roleName=roleRepo.getRoleName(roles.getRoleName());
            if(roleName !=null) return new ResponseEntity <> ( "Role Already exist" , HttpStatus.OK );
            return new ResponseEntity <> ( roleService.addRoles(roles) , HttpStatus.CREATED );
        }
        @GetMapping("/home")
        String home()
        {
            return "this is Home Page";
        }

    /*
    fetch & filter Controller
     */

    @GetMapping ( "/get-all")
    public ResponseEntity < List < Roles > > getAllRoles ( )
        {
            return new ResponseEntity <> ( this.roleService.getAllRoles(), HttpStatus.OK );
        }

    @GetMapping ( "/role/{id}")
    public ResponseEntity < ? > getRoleById ( @PathVariable Long id )
        {
            Roles roles=roleRepo.getRoleById(id);
            if(roles==null) return new ResponseEntity <> ( "Role Not Found "+id , HttpStatus.NOT_FOUND );
            return new ResponseEntity <> ( roleService.getRolesByID ( id ) , HttpStatus.OK );
        }

        /*
        Remove & Delete Controller
         */

    @DeleteMapping ( "/role")
    public void removeAllRoles ( )
        {
            roleService.removeAll ( );
        }

    @DeleteMapping ( "role/{id}")
    public ResponseEntity<?> removeRolesById (@PathVariable Long id )
        {
            Optional<Roles> roles= roleRepo.findById(id);
            if (roles ==null)
                return new ResponseEntity<>("Role not available",HttpStatus.NOT_FOUND);
            else {
                roleService.removeById(id);
                return new ResponseEntity<>("Deleted Role" + id, HttpStatus.OK);
            }
        }
}
