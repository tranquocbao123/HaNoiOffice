package com.example.cs_office.Service;


import com.example.cs_office.Model.Entity.Scheduledetail;
import com.example.cs_office.Repository.ScheduleDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleDetailService {
    @Autowired
    private final ScheduleDetailRepository scheduleDetailRepository;

    public ScheduleDetailService(ScheduleDetailRepository scheduleDetailRepository) {
        this.scheduleDetailRepository = scheduleDetailRepository;
    }

    public List<Scheduledetail> getScheduledetail() {

        return scheduleDetailRepository.findAll();
    }

    public List<Scheduledetail> getScheduledetailByStatus(boolean status) {

        return scheduleDetailRepository.findScheduledetailByStatus(status);
    }

    public Optional<Scheduledetail> getScheduledetailById(int scheduledetailId) {
        Optional<Scheduledetail> scheduledetail = scheduleDetailRepository.findById(scheduledetailId);
        return scheduledetail;
    }

    public void addNewScheduledetail(Scheduledetail scheduledetail) {
        Optional<Scheduledetail> scheduledetailOptional =
                scheduleDetailRepository.findScheduledetailById(scheduledetail.getId());
        if (scheduledetailOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        scheduleDetailRepository.save(scheduledetail);
    }

    public void deleteScheduledetail(int scheduleDetailId) {
        boolean exists = scheduleDetailRepository.existsById(scheduleDetailId);
        if (!exists) {
            throw new IllegalStateException("schedule detail with id " + scheduleDetailId + " does not exists");
        }
        scheduleDetailRepository.deleteById(scheduleDetailId);
    }

    @Transactional
    public Scheduledetail updateScheduledetailStatus(Scheduledetail scheduledetail) {
        scheduledetail.setStatus(false);
        return scheduleDetailRepository.save(scheduledetail);
    }

    @Transactional
    public Scheduledetail updateScheduledetailBlack(Scheduledetail scheduledetail) {
        scheduledetail.setStatus(true);
        return scheduleDetailRepository.save(scheduledetail);
    }

    @Transactional
    public Scheduledetail updateScheduledetail(Scheduledetail scheduledetail, int scheduledetailId){
        Scheduledetail scheduledetail1 = this.scheduleDetailRepository.getOne(scheduledetailId);
        BeanUtils.copyProperties(scheduledetail,scheduledetail1);
        return scheduleDetailRepository.saveAndFlush(scheduledetail1);
    }
}
