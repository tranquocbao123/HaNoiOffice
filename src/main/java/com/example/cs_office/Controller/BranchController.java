package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Service.BranchService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.BRANCH)
@CrossOrigin("*")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    //list branch
    @GetMapping(PathResources.FIND_ALL)
    public List<Branch> getBranch() {
        return branchService.getBranch();
    }

    //list branch by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Branch> getBranchByStatusFalse() {
        return branchService.getBranchByStatus(false);
    }

    //list branch by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Branch> getBranchByStatusTrue() {
        return branchService.getBranchByStatus(true);
    }

    //search branch by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Branch> getById(
            @PathVariable("id") int branchId) {
        return branchService.getById(branchId);
    }

    // insert branch
    @PostMapping(PathResources.SAVE)
    public void insertBranch(@RequestBody Branch branch) {
        branchService.addNewBranch(branch);
    }

    //search branch by name
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<Branch> getBranchByName(
            @PathVariable("name") String bracnchName) {
        return branchService.getBranchByName(bracnchName);
    }

    //delete branch by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteBranch(
            @PathVariable("id") int branchId) {
        branchService.deleteBranch(branchId);
    }

    //update branch by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateBranchStatus(
            @RequestBody Branch branch
    ) {
        branchService.updateBranchStatus(branch);
    }

    //update staff black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateStaffBlack(
            @RequestBody Branch branch
    ) {
        branchService.updateBranchBlack(branch);
    }

    //update branch by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Branch updateBranch
    (@RequestBody Branch branch,
     @PathVariable("id") int branchId) {
        return branchService.updateBranch(branch, branchId);
    }

}
