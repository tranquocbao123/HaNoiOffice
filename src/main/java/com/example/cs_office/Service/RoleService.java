package com.example.cs_office.Service;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Repository.CustomerRepository;
import com.example.cs_office.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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
    public void updateRole(int roleId, String name, Date create_Date, boolean status) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("role with id" + roleId + "does not exists"));
//        if(fullname != null && fullname.length() > 0 && !Objects.equals(user.getFullname(),fullname)){
//            customer.setFullname(fullname);
//        }
    }
}
