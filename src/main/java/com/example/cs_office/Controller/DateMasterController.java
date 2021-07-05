package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.DateMasters;
import com.example.cs_office.Service.DateMasterService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.DATEMASTER)
public class DateMasterController {

    @Autowired
    private final DateMasterService dateMasterService;

    public DateMasterController(DateMasterService dateMasterService) {
        this.dateMasterService = dateMasterService;
    }

    //list dateMaster
    @GetMapping(PathResources.FIND_ALL)
    public List<DateMasters> getDateMaster() {
        return dateMasterService.getDateMaster();
    }

    //list dateMaster by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<DateMasters> getDateMasterByStatusFalse() {
        return dateMasterService.getDateMasterByStatus(false);
    }

    //list dateMaster by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<DateMasters> getDateMasterByStatusTrue() {
        return dateMasterService.getDateMasterByStatus(true);
    }

    //search dateMaster by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<DateMasters> getById(
            @PathVariable("id") int datemasterId) {
        return dateMasterService.getDateMasterById(datemasterId);
    }

    // insert dateMaster
    @PostMapping(PathResources.SAVE)
    public void insertDateMaster(@RequestBody DateMasters dateMasters) {
        dateMasterService.addNewdateMaster(dateMasters);
    }

    //delete datemaster by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteDateMaster(
            @PathVariable("id") int datemasterId) {
        dateMasterService.deleteDateMaster(datemasterId);
    }

    //update datemaster by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateDateMasterStatus(
            @RequestBody DateMasters dateMasters
    ) {
        dateMasterService.updateDateMastersStatus(dateMasters);
    }

    //update datemaster black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateDateMastersBlack(
            @RequestBody DateMasters dateMasters
    ) {
        dateMasterService.updateDateMastersBlack(dateMasters);
    }

    @PutMapping(path = PathResources.UPDATEBYID)
    public DateMasters updateDateMaster
            (@RequestBody DateMasters dateMasters,
             @PathVariable("id") int datemasterId) {
        return dateMasterService.updateDateMaster(dateMasters, datemasterId);
    }

}
