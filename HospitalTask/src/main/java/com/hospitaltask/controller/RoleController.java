package com.hospitaltask.controller;

import com.hospitaltask.entity.Roles;
import com.hospitaltask.repository.RoleRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.RoleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/HM/role")
public class RoleController {
    private final String Record_Not_Found = "Record Not Found";
    private final String Record_Found_Success = "Record Found Success";
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepo roleRepo;

    //add & Update Controller
    @PostMapping("/save")
    public ResponseEntity<?> addRoles(@RequestBody @NotNull Roles roles) {
        String roleName = roleRepo.getRoleName(roles.getRoleName());
        if (roleName != null)
            return CustomResponseHandler.response("Role Already exist", HttpStatus.ALREADY_REPORTED, roles);
        return CustomResponseHandler.response("Role Created ", HttpStatus.CREATED, roles);
    }

    // fetch & filter Controller
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllRoles() {
        return CustomResponseHandler.response(Record_Found_Success, HttpStatus.OK, roleService.getAllRoles());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        Roles roles = roleRepo.getRoleById(id);
        if (roles == null) return CustomResponseHandler.response(Record_Not_Found, HttpStatus.NOT_FOUND, id);
        return CustomResponseHandler.response(Record_Found_Success, HttpStatus.OK, roleService.getRolesByID(id));
    }

    // Remove & Delete Controller
    @DeleteMapping("/role")
    public void removeAllRoles() {
        roleService.removeAll();
    }

    @DeleteMapping("role/{id}")
    public ResponseEntity<?> removeRolesById(@PathVariable Long id) {
        Optional<Roles> roles = roleRepo.findById(id);
        if (roles == null) return CustomResponseHandler.response(Record_Found_Success, HttpStatus.NOT_FOUND, id);
        else {
            roleService.removeById(id);
            return CustomResponseHandler.response("Record Deleted ", HttpStatus.OK, id);
        }
    }
}