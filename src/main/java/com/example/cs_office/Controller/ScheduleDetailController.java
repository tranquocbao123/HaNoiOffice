package com.example.cs_office.Controller;

import com.example.cs_office.Model.Schedule;
import com.example.cs_office.Model.Scheduledetail;
import com.example.cs_office.Service.ScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scheduledetail")
@CrossOrigin("*")
public class ScheduleDetailController {
    @Autowired
    private final ScheduleDetailService scheduleDetailService;

    public ScheduleDetailController(ScheduleDetailService scheduleDetailService) {
        this.scheduleDetailService = scheduleDetailService;
    }

    //list schedule detail
    @GetMapping()
    public List<Scheduledetail> getScheduleDetail() {
        return scheduleDetailService.getScheduledetail();
    }

    //list schedule detail by status == fasle
    @GetMapping("/false")
    public List<Scheduledetail> getScheduledetailByStatusFalse() {
        return scheduleDetailService.getScheduledetailByStatus(false);
    }

    //list schedule detail by status == true
    @GetMapping("/true")
    public List<Scheduledetail> getScheduledetailByStatusTrue() {
        return scheduleDetailService.getScheduledetailByStatus(true);
    }

    // insert schedule detail
    @PostMapping
    public void insertScheduleDetail(@RequestBody Scheduledetail scheduledetail) {
        scheduleDetailService.addNewScheduledetail(scheduledetail);
    }

    //search schedule detail by id
    @GetMapping(path = "{scheduledetailId}")
    public Optional<Scheduledetail> getScheduleById(
            @PathVariable("scheduledetailId") int scheduledetailId) {
        return scheduleDetailService.getScheduledetailById(scheduledetailId);
    }

    //delete schedule detail by id
    @DeleteMapping(path = "{scheduledetailId}")
    public void deleteScheduleDetail(
            @PathVariable("scheduledetailId") int scheduledetailId) {
        scheduleDetailService.deleteScheduledetail(scheduledetailId);
    }

    //update schedule detail by id
    @PutMapping(path = "/{scheduledetailId}")
    public Scheduledetail updateScheduledetail
    (@RequestBody Scheduledetail scheduledetail,
     @PathVariable("scheduledetailId") int id) {
        return scheduleDetailService.updateScheduledetail(scheduledetail, id);
    }
}
