package com.example.cs_office.Service;

import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Role;
import com.example.cs_office.Model.Entity.Staff;
import com.example.cs_office.Repository.BranchRepotitory;
import com.example.cs_office.Repository.RoleRepository;
import com.example.cs_office.Repository.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BranchRepotitory branchRepotitory;

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
        Optional<Staff> staff = staffRepository.findById(staffId);
        return staff;
    }

    public List<Staff> getStaffByUserName(String staffUserName) {
        List<Staff> staff = staffRepository.findStaffByUserName(staffUserName);
        return staff;
    }

    public Staff getStaffByEmail(String email) {
        Staff staff = staffRepository.findStaffByEmail(email);
        return staff;
    }

    public List<Staff> getListStaff(String idRole, String idBranch, boolean gender, String nameStaff) {
        List<Staff> listStaff = staffRepository.get_list_staff(idRole, idBranch, gender, nameStaff);
        return listStaff;
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
            throw new IllegalStateException("staff with id " + staffId + " does not exists");
        }
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public void updateStaffStatus(Staff staff) {
        staff.setStatus(false);
        staffRepository.save(staff);
    }

    @Transactional
    public void updateStaffBlack(Staff staff) {
        staff.setStatus(true);
        staffRepository.save(staff);
    }

    @Transactional
    public Staff updateStaff(com.example.cs_office.Model.Dto.Staff staffDto, int staffId) {
        Staff user = new Staff();
        user.setId(staffDto.getId());
        user.setCodeStaff(staffDto.getCodeStaff());
        user.setFullName(staffDto.getFullName());
        user.setGender(staffDto.isGender());
        user.setBirthDay(staffDto.getBirthDay());
        user.setPhoneNumber(staffDto.getPhoneNumber());
        user.setAddress(staffDto.getAddress());
        user.setEmail(staffDto.getEmail());
        user.setUserName(staffDto.getUserName());
        user.setPassword(staffDto.getPassword());
        user.setQueQuan(staffDto.getQueQuan());
        user.setHktt(staffDto.getHktt());
        user.setDescription(staffDto.getDescription());
        Optional<Role> role = roleRepository.findRoleById(staffDto.getRole());
        user.setRole(role.get());
        Optional<Branch> branch = branchRepotitory.findBranchById(staffDto.getBranch());
        user.setBranch(branch.get());
        Staff staff1 = this.staffRepository.getOne(staffId);
        BeanUtils.copyProperties(user, staff1);
        return staffRepository.saveAndFlush(staff1);
    }

}
