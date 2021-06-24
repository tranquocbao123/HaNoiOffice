package com.example.cs_office.Controller;

import com.example.cs_office.Model.DateMasters;
import com.example.cs_office.Service.DateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datemaster")
public class DateMasterController {

    @Autowired
    private final DateMasterService dateMasterService;

    public DateMasterController(DateMasterService dateMasterService) {
        this.dateMasterService = dateMasterService;
    }

    //list dateMaster
    @GetMapping()
    public List<DateMasters> getDateMaster() {
        return dateMasterService.getDateMaster();
    }

    //list dateMaster by status == fasle
    @GetMapping("/false")
    public List<DateMasters> getDateMasterByStatusFalse() {
        return dateMasterService.getDateMasterByStatus(false);
    }

    //list dateMaster by status == true
    @GetMapping("/true")
    public List<DateMasters> getDateMasterByStatusTrue() {
        return dateMasterService.getDateMasterByStatus(true);
    }

    //search dateMaster by id
    @GetMapping(path = "{datemasterId}")
    public Optional<DateMasters> getById(
            @PathVariable("datemasterId") int datemasterId) {
        return dateMasterService.getDateMasterById(datemasterId);
    }

    // insert dateMaster
    @PostMapping
    public void insertDateMaster(@RequestBody DateMasters dateMasters) {
        dateMasterService.addNewdateMaster(dateMasters);
    }

    //delete datemaster by id
    @DeleteMapping(path = "{datemasterId}")
    public void deleteDateMaster(
            @PathVariable("datemasterId") int datemasterId) {
        dateMasterService.deleteDateMaster(datemasterId);
    }

    @PutMapping(path = "/{datemasterId}")
    public DateMasters updateDateMaster
            (@RequestBody DateMasters dateMasters,
             @PathVariable("datemasterId") int id) {
        return dateMasterService.updateDateMaster(dateMasters, id);
    }

}
