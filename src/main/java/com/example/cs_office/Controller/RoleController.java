package com.example.cs_office.Controller;


import com.example.cs_office.Model.Entity.Role;
import com.example.cs_office.Service.RoleService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.ROLE)
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //list role
    @GetMapping(PathResources.FIND_ALL)
    public List<Role> getRole() {
        return roleService.getRole();
    }

    //list role by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Role> getRoleByStatusFalse() {
        return roleService.getRoleByStatus(false);
    }

    //list role by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Role> getRoleByStatusTrue() {
        return roleService.getRoleByStatus(true);
    }

    //search role by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Role> getById(
            @PathVariable("id") int roleId) {
        return roleService.getById(roleId);
    }
    
    // insert role
    @PostMapping(PathResources.SAVE)
    public void insertRole(@RequestBody Role role) {
        roleService.addNewRole(role);
    }

    //search role by name
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<Role> getRoleByName(
            @PathVariable("name") String roleName) {
        return roleService.getRoleByName(roleName);
    }

    //delete role by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteRole(
            @PathVariable("id") int roleId) {
        roleService.deleteRole(roleId);
    }

    //update role by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateRoleStatus(
            @RequestBody Role role
    ) {
        roleService.updateRoleStatus(role);
    }

    //update role black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateRoleBlack(
            @RequestBody Role role
    ) {
        roleService.updateRoleBlack(role);
    }

    @PutMapping(path = PathResources.UPDATEBYID)
    public Role updateRole
    (@RequestBody Role role,
     @PathVariable("id") int roleId) {
        return roleService.updateRole(role, roleId);
    }

}
