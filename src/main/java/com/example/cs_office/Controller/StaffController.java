package com.example.cs_office.Controller;

import com.example.cs_office.Model.Staff;
import com.example.cs_office.Service.StaffService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.STAFF)
@CrossOrigin("*")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    //list staff
    @GetMapping(PathResources.FIND_ALL)
    public List<Staff> getStaff() {
        return staffService.getStaff();
    }

    //list staff by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Staff> getStaffByStatusFalse() {
        return staffService.getStaffByStatus(false);
    }

    //list staff by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Staff> getStaffByStatusTrue() {
        return staffService.getStaffByStatus(true);
    }

    //search staff by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Staff> getById(
            @PathVariable("id") int staffId) {
        return staffService.getById(staffId);
    }

    // insert staff
    @PostMapping(PathResources.SAVE)
    public void insertStaff(@RequestBody Staff staff) {
        staffService.addNewStaff(staff);
    }

    //search staff by username
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<Staff> getStaffByUserName(
            @PathVariable("name") String staffUsername) {
        return staffService.getStaffByUserName(staffUsername);
    }

    //delete staff by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteStaff(
            @PathVariable("id") int staffId) {
        staffService.deleteStaff(staffId);
    }

    //update staff by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateStaffStatus(
            @RequestBody Staff staff
    ) {
        staffService.updateStaffStatus(staff);
    }

    //update staff black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateStaffBlack(
            @RequestBody Staff staff
    ) {
        staffService.updateStaffBlack(staff);
    }

    @PutMapping(path = PathResources.UPDATEBYID)
    public Staff updateStaff
            (@RequestBody Staff staff,
             @PathVariable("id") int staffId) {
        return staffService.updateStaff(staff, staffId);
    }

}
