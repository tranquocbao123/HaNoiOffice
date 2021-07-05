package com.example.cs_office.Service;

import com.example.cs_office.Model.Schedule;
import com.example.cs_office.Repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedule() {

        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByStatus(boolean status) {

        return scheduleRepository.findScheduleByStatus(status);
    }

    public Optional<Schedule> getScheduleById(int scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        return schedule;
    }

    public void addNewSchedule(Schedule schedule) {
        Optional<Schedule> scheduleOptional =
                scheduleRepository.findScheduleById(schedule.getId());
        if (scheduleOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int scheduleId) {
        boolean exists = scheduleRepository.existsById(scheduleId);
        if (!exists) {
            throw new IllegalStateException("schedule with id " + scheduleId + " does not exists");
        }
        scheduleRepository.deleteById(scheduleId);
    }

    @Transactional
    public Schedule updateScheduleStatus(Schedule schedule) {
        schedule.setStatus(false);
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public Schedule updateScheduleBlack(Schedule schedule) {
        schedule.setStatus(true);
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public Schedule updateSchedule(Schedule schedule, int scheduleId){
        Schedule schedule1 = this.scheduleRepository.getOne(scheduleId);
        BeanUtils.copyProperties(schedule,schedule1);
        return scheduleRepository.saveAndFlush(schedule1);
    }
}
