package com.example.cs_office.Service;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Repository.RoleRepository;
import com.example.cs_office.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {

        this.staffRepository = staffRepository;
    }

    public List<Staff> getStaff() {

        return staffRepository.findAll();
    }

    public List<Staff> getStaffByStatus(boolean status) {

        return staffRepository.findStaffByStatus(status);
    }

    public Optional<Staff> getById(int staffId) {
        Optional<Staff> role = staffRepository.findById(staffId);
        return role;
    }

    public void addNewStaff(Staff staff) {
        Optional<Staff> staffOptional =
                staffRepository.findStaffById(staff.getId());
        if (staffOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }   
        staffRepository.save(staff);
    }

    public void deleteStaff(int staffId) {
        boolean exists = staffRepository.existsById(staffId);
        if (!exists) {
            throw new IllegalStateException("role with id " + staffId + " does not exists");
        }
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public void updateStaff(int staffId, String userName, String password, String phoneNumber, String email, String address, Branch branch, Role role, Date create_Date, boolean status) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new IllegalStateException("role with id" + staffId + "does not exists"));
    }

}
