package com.example.cs_office.Controller;

import com.example.cs_office.Model.Room;
import com.example.cs_office.Model.Schedule;
import com.example.cs_office.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //list schedule
    @GetMapping()
    public List<Schedule> getSchedule() {
        return scheduleService.getSchedule();
    }

    //list schedule by status == fasle
    @GetMapping("/false")
    public List<Schedule> getScheduleByStatusFalse() {
        return scheduleService.getScheduleByStatus(false);
    }

    //list schedule by status == true
    @GetMapping("/true")
    public List<Schedule> getScheduleByStatusTrue() {
        return scheduleService.getScheduleByStatus(true);
    }

    // insert schedule
    @PostMapping
    public void insertSchedule(@RequestBody Schedule schedule) {
        scheduleService.addNewSchedule(schedule);
    }

    //search schedule by id
    @GetMapping(path = "{scheduleId}")
    public Optional<Schedule> getScheduleById(
            @PathVariable("scheduleId") int scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    //delete schedule by id
    @DeleteMapping(path = "{scheduleId}")
    public void deleteSchedule(
            @PathVariable("scheduleId") int scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

    //update schedule by id
    @PutMapping(path = "/{scheduleId}")
    public Schedule updateSchedule
    (@RequestBody Schedule schedule,
     @PathVariable("scheduleId") int id) {
        return scheduleService.updateSchedule(schedule, id);
    }
}
