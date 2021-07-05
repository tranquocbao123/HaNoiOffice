package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Role;
import com.example.cs_office.Repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public List<Role> getRole() {

        return roleRepository.findAll();
    }

    public List<Role> getRoleByStatus(boolean status) {

        return roleRepository.findRoleByStatus(status);
    }

    public Optional<Role> getById(int userId) {
        Optional<Role> role = roleRepository.findById(userId);
        return role;
    }

    public List<Role> getRoleByName(String roleName) {
        List<Role> role = roleRepository.findRoleByName(roleName);
        return role;
    }

    public void addNewRole(Role role) {
        Optional<Role> roleOptional =
                roleRepository.findRoleById(role.getId());
        if (roleOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        roleRepository.save(role);
    }

    public void deleteRole(int roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if (!exists) {
            throw new IllegalStateException("role with id " + roleId + " does not exists");
        }
        roleRepository.deleteById(roleId);
    }


    @Transactional
    public Role updateRoleStatus(Role role) {
        role.setStatus(false);
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRoleBlack(Role role) {
        role.setStatus(true);
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(Role role, int roleId) {
        Role role1 = this.roleRepository.getOne(roleId);
        BeanUtils.copyProperties(role, role1);
        return roleRepository.saveAndFlush(role1);
    }

}
