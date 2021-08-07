package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.Service;
import com.example.cs_office.Model.Entity.Shift;
import com.example.cs_office.Service.ShiftService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.SHIFT)
@CrossOrigin("*")
public class ShiftController {
    @Autowired
    private final ShiftService shiftService;


    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    //list shift
    @GetMapping(PathResources.FIND_ALL)
    public List<Shift> getShift() {
        return shiftService.getShift();
    }

    //list shift by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Shift> getShiftByStatusFalse() {
        return shiftService.getShiftByStatus(false);
    }

    //list shift by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Shift> getShiftByStatusTrue() {
        return shiftService.getShiftByStatus(true);
    }

    // insert shift
    @PostMapping(PathResources.SAVE)
    public void insertShift(@RequestBody Shift shift) {
        shiftService.addNewShift(shift);
    }

    //search shift by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Shift> getShiftById(
            @PathVariable("id") int shiftId) {
        return shiftService.getShiftById(shiftId);
    }


    //delete shift by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteShift(
            @PathVariable("id") int shiftId) {
        shiftService.deleteShift(shiftId);
    }

    //update shift by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateShiftStatus(
            @RequestBody Shift shift
    ) {
        shiftService.updateShiftStatus(shift);
    }

    //update shift black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateServiceBlack(
            @RequestBody Shift shift
    ) {
        shiftService.updateShiftBlack(shift);
    }

    //update shift by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Shift updateShift
    (@RequestBody Shift shift,
     @PathVariable("id") int shiftId) {
        return shiftService.updateShift(shift, shiftId);
    }
}
