package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
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
    @GetMapping()
    public List<Branch> getBranch() {
        return branchService.getBranch();
    }

    //list branch by status == fasle
    @GetMapping("/false")
    public List<Branch> getBranchByStatusFalse() {
        return branchService.getBranchByStatus(false);
    }

    //list branch by status == true
    @GetMapping("/true")
    public List<Branch> getBranchByStatusTrue() {
        return branchService.getBranchByStatus(true);
    }

    //search branch by id
    @GetMapping(path = "{branchId}")
    public Optional<Branch> getById(
            @PathVariable("branchId") int branchId) {
        return branchService.getById(branchId);
    }

    // insert branch
    @PostMapping
    public void insertBranch(@RequestBody Branch branch) {
        branchService.addNewBranch(branch);
    }

    //search branch by name
    @GetMapping(path = "searchname/{bracnchName}")
    public List<Branch> getBranchByName(
            @PathVariable("bracnchName") String bracnchName) {
        return branchService.getBranchByName(bracnchName);
    }

    //delete branch by id
    @DeleteMapping(path = "{branchId}")
    public void deleteBranch(
            @PathVariable("branchId") int branchId) {
        branchService.deleteBranch(branchId);
    }

    //update branch by id
    @PutMapping(path = "/{branchId}")
    public Branch updateBranch
    (@RequestBody Branch branch,
     @PathVariable("branchId") int id) {
        return branchService.updateBranch(branch, id);
    }

}
