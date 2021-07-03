package com.example.cs_office.Controller;

import com.example.cs_office.Model.Schedule;
import com.example.cs_office.Service.ScheduleService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.SCHEDULE)
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //list schedule
    @GetMapping(PathResources.FIND_ALL)
    public List<Schedule> getSchedule() {
        return scheduleService.getSchedule();
    }

    //list schedule by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Schedule> getScheduleByStatusFalse() {
        return scheduleService.getScheduleByStatus(false);
    }

    //list schedule by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Schedule> getScheduleByStatusTrue() {
        return scheduleService.getScheduleByStatus(true);
    }

    // insert schedule
    @PostMapping(PathResources.SAVE)
    public void insertSchedule(@RequestBody Schedule schedule) {
        scheduleService.addNewSchedule(schedule);
    }

    //search schedule by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Schedule> getScheduleById(
            @PathVariable("id") int scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    //delete schedule by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteSchedule(
            @PathVariable("id") int scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

    //update schedule by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateScheduleStatus(
            @RequestBody Schedule schedule
    ) {
        scheduleService.updateScheduleStatus(schedule);
    }

    //update schedule black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateScheduleBlack(
            @RequestBody Schedule schedule
    ) {
        scheduleService.updateScheduleBlack(schedule);
    }

    //update schedule by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Schedule updateSchedule
    (@RequestBody Schedule schedule,
     @PathVariable("id") int scheduleId) {
        return scheduleService.updateSchedule(schedule, scheduleId);
    }

}
