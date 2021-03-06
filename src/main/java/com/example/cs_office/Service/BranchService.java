package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Repository.BranchRepotitory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final BranchRepotitory branchRepotitory;

    @Autowired
    public BranchService(BranchRepotitory branchRepotitory) {
        this.branchRepotitory = branchRepotitory;
    }

    public List<Branch> getBranch() {
        return branchRepotitory.findAll();
    }

    public List<Branch> getBranchByStatus(boolean status) {

        return branchRepotitory.findBranchByStatus(status);
    }

    public Optional<Branch> getById(int branchId) {
        Optional<Branch> branch = branchRepotitory.findById(branchId);
        return branch;
    }

    public List<Branch> getBranchByName(String branchName) {
        List<Branch> branch = branchRepotitory.findBranchByName(branchName);
        return branch;
    }

    public void addNewBranch(Branch branch) {
        Optional<Branch> branchOptional =
                branchRepotitory.findBranchById(branch.getId());
        if (branchOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        branchRepotitory.save(branch);
    }

    public void deleteBranch(int branchId) {
        boolean exists = branchRepotitory.existsById(branchId);
        if (!exists) {
            throw new IllegalStateException("branch with id " + branchId + " does not exists");
        }
        branchRepotitory.deleteById(branchId);
    }

    @Transactional
    public Branch updateBranchStatus(Branch branch) {
        branch.setStatus(false);
        return branchRepotitory.save(branch);
    }

    @Transactional
    public Branch updateBranchBlack(Branch branch) {
        branch.setStatus(true);
        return branchRepotitory.save(branch);
    }

    @Transactional
    public Branch updateBranch(Branch branch, int branchId){
        Branch branch1 = this.branchRepotitory.getOne(branchId);
        BeanUtils.copyProperties(branch,branch1);
        return branchRepotitory.saveAndFlush(branch1);
    }

}
