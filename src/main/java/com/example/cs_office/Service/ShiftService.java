package com.example.cs_office.Service;


import com.example.cs_office.Model.Entity.Shift;
import com.example.cs_office.Repository.ShiftRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getShift() {
        return shiftRepository.findAll();
    }

    public List<Shift> getShiftByStatus(boolean status) {

        return shiftRepository.findShiftByStatus(status);
    }

    public Optional<Shift> getShiftById(int serviceId) {
        Optional<Shift> shift = shiftRepository.findById(serviceId);
        return shift;
    }

    public void addNewShift(Shift shift) {
        Optional<Shift> shiftOptional =
                shiftRepository.findShiftById(shift.getId());
        if (shiftOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        shiftRepository.save(shift);
    }

    public void deleteShift(int shiftId) {
        boolean exists = shiftRepository.existsById(shiftId);
        if (!exists) {
            throw new IllegalStateException("service with id " + shiftId + " does not exists");
        }
        shiftRepository.deleteById(shiftId);
    }

    @Transactional
    public Shift updateShiftStatus(Shift shift) {
        shift.setStatus(false);
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift updateShiftBlack(Shift shift) {
        shift.setStatus(true);
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift updateShift(Shift shift, int shiftId){
        Shift shift1 = this.shiftRepository.getOne(shiftId);
        BeanUtils.copyProperties(shift,shift1);
        return shiftRepository.saveAndFlush(shift1);
    }
}
