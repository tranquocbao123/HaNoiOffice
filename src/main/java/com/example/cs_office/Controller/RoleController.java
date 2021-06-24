package com.example.cs_office.Controller;


import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Model.TypeRoom;
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

    // insert role
    @PostMapping
    public void insertRole(@RequestBody Role role) {
        roleService.addNewRole(role);
    }

    //search role by id
    @GetMapping(path = "{roleId}")
    public Optional<Role> getById(
            @PathVariable("roleId") int roleId) {
        return roleService.getById(roleId);
    }
// search name
@GetMapping(path = "/rolename/{name}")
public List<Role> searchName(@PathVariable("name")String name) {
    return  roleService.getRolename(name);
}
    //delete role by id
    @DeleteMapping(path = "{roleId}")
    public void deleteRole(
            @PathVariable("roleId") int roleId) {
        roleService.deleteRole(roleId);
    }

    //update role by id
    @PutMapping(path = "/{roleId}")
    public Role updateRole
    (@RequestBody Role role,
     @PathVariable("roleId") int id) {
        return roleService.updateRole(role, id);
    }
}
