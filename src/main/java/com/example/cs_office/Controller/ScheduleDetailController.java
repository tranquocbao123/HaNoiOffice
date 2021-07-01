package com.example.cs_office.Controller;

import com.example.cs_office.Model.Scheduledetail;
import com.example.cs_office.Service.ScheduleDetailService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.SCHEDULEDETAIL)
@CrossOrigin("*")
public class ScheduleDetailController {
    @Autowired
    private final ScheduleDetailService scheduleDetailService;

    public ScheduleDetailController(ScheduleDetailService scheduleDetailService) {
        this.scheduleDetailService = scheduleDetailService;
    }

    //list schedule detail
    @GetMapping(PathResources.FIND_ALL)
    public List<Scheduledetail> getScheduleDetail() {
        return scheduleDetailService.getScheduledetail();
    }

    //list schedule detail by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Scheduledetail> getScheduledetailByStatusFalse() {
        return scheduleDetailService.getScheduledetailByStatus(false);
    }

    //list schedule detail by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Scheduledetail> getScheduledetailByStatusTrue() {
        return scheduleDetailService.getScheduledetailByStatus(true);
    }

    // insert schedule detail
    @PostMapping(PathResources.SAVE)
    public void insertScheduleDetail(@RequestBody Scheduledetail scheduledetail) {
        scheduleDetailService.addNewScheduledetail(scheduledetail);
    }

    //search schedule detail by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Scheduledetail> getScheduleById(
            @PathVariable("id") int scheduledetailId) {
        return scheduleDetailService.getScheduledetailById(scheduledetailId);
    }

    //delete schedule detail by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteScheduleDetail(
            @PathVariable("id") int scheduledetailId) {
        scheduleDetailService.deleteScheduledetail(scheduledetailId);
    }

    //update schedule detail by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateScheduledetailStatus(
            @RequestBody Scheduledetail scheduledetail
    ) {
        scheduleDetailService.updateScheduledetailStatus(scheduledetail);
    }

    //update schedule detail black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateScheduledetailBlack(
            @RequestBody Scheduledetail scheduledetail
    ) {
        scheduleDetailService.updateScheduledetailBlack(scheduledetail);
    }

    //update schedule detail by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Scheduledetail updateScheduledetail
    (@RequestBody Scheduledetail scheduledetail,
     @PathVariable("id") int scheduledetailId) {
        return scheduleDetailService.updateScheduledetail(scheduledetail, scheduledetailId);
    }

}
