package com.example.cs_office.Controller;


import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Service.CustomerService;
import com.example.cs_office.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //list role
    @GetMapping()
    public List<Role> getRole() {
        return roleService.getRole();
    }

    //list role by status == fasle
    @GetMapping("/false")
    public List<Role> getRoleByStatusFalse() {
        return roleService.getRoleByStatus(false);
    }

    //list role by status == true
    @GetMapping("/true")
    public List<Role> getRoleByStatusTrue() {
        return roleService.getRoleByStatus(true);
    }

    //search role by id
    @GetMapping(path = "{roleId}")
    public Optional<Role> getById(
            @PathVariable("roleId") int roleId) {
        return roleService.getById(roleId);
    }
    
    // insert role
    @PostMapping
    public void insertRole(@RequestBody Role role) {
        roleService.addNewRole(role);
    }

    //delete role by id
    @DeleteMapping(path = "{roleId}")
    public void deleteRole(
            @PathVariable("roleId") int roleId) {
        roleService.deleteRole(roleId);
    }

    //update role by id
    @PutMapping()
    public void updateRole(
            @RequestBody Role role
    ) {
        roleService.updateRole(role);
    }

    //update role black by id
    @PutMapping(path = "/black")
    public void updateRoleBlack(
            @RequestBody Role role
    ) {
        roleService.updateRoleBlack(role);
    }

}
