package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    //list staff
    @GetMapping()
    public List<Staff> getStaff() {
        return staffService.getStaff();
    }

    //list staff by status == fasle
    @GetMapping("/false")
    public List<Staff> getStaffByStatusFalse() {
        return staffService.getStaffByStatus(false);
    }

    //list staff by status == true
    @GetMapping("/true")
    public List<Staff> getStaffByStatusTrue() {
        return staffService.getStaffByStatus(true);
    }

    // insert staff
    @PostMapping
    public void insertStaff(@RequestBody Staff staff) {
        staffService.addNewStaff(staff);
    }

    //search staff by id
    @GetMapping(path = "{staffId}")
    public Optional<Staff> getById(
            @PathVariable("staffId") int staffId) {
        return staffService.getById(staffId);
    }

    //delete staff by id
    @DeleteMapping(path = "{staffId}")
    public void deleteStaff(
            @PathVariable("staffId") int staffId) {
        staffService.deleteStaff(staffId);
    }

    //update staff by id
    @PutMapping(path = "{staffId}")
    public void updateStaff(
            @PathVariable("staffId") int staffId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Branch branch,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Date createDate,
            @RequestParam(required = false) boolean status
    ) {
        staffService.updateStaff(staffId, userName, password, phoneNumber, email, address, branch, role, createDate, status);
    }

}
