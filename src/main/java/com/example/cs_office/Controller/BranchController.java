package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
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

    // insert branch
    @PostMapping
    public void insertBranch(@RequestBody Branch branch) {
        branchService.addNewBranch(branch);
    }

    //search branch by id
    @GetMapping(path = "{branchId}")
    public Optional<Branch> getById(
            @PathVariable("branchId") int branchId) {
        return branchService.getById(branchId);
    }
    // search branch name
    @GetMapping(path = "/brachname/{name}")
public List<Branch> search(@PathVariable("name") String name){
        return branchService.getBranchByName(name);
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
