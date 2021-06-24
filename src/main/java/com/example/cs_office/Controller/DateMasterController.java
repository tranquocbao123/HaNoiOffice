package com.example.cs_office.Controller;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.DateMaster;
import com.example.cs_office.Service.DateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public List<DateMaster> getDateMaster() {
        return dateMasterService.getDateMaster();
    }

    //list dateMaster by status == fasle
    @GetMapping("/false")
    public List<DateMaster> getDateMasterByStatusFalse() {
        return dateMasterService.getDateMasterByStatus(false);
    }

    //list dateMaster by status == true
    @GetMapping("/true")
    public List<DateMaster> getDateMasterByStatusTrue() {
        return dateMasterService.getDateMasterByStatus(true);
    }

    //search dateMaster by id
    @GetMapping(path = "{datemasterId}")
    public Optional<DateMaster> getById(
            @PathVariable("datemasterId") int datemasterId) {
        return dateMasterService.getDateMasterById(datemasterId);
    }

    // insert dateMaster
    @PostMapping
    public void insertDateMaster(@RequestBody DateMaster dateMaster) {
        dateMasterService.addNewdateMaster(dateMaster);
    }

    //delete datemaster by id
    @DeleteMapping(path = "{datemasterId}")
    public void deleteDateMaster(
            @PathVariable("datemasterId") int datemasterId) {
        dateMasterService.deleteDateMaster(datemasterId);
    }

<<<<<<< HEAD
    //update customer by id
    @PutMapping()
    public void updateCustomer(
            @RequestBody DateMaster dateMaster
    ) {
        dateMasterService.updateDateMaster(dateMaster);
    }

    //update customer black by id
    @PutMapping()
    public void updateCustomerBlack(
            @RequestBody DateMaster dateMaster
    ) {
        dateMasterService.updateDateMasterBlack(dateMaster);
=======
    @PutMapping(path = "/{datemasterId}")
    public DateMaster updateDateMaster
            (@RequestBody DateMaster dateMaster,
             @PathVariable("datemasterId") int id) {
        return dateMasterService.updateDateMaster(dateMaster, id);
>>>>>>> origin/vannh
    }

}
