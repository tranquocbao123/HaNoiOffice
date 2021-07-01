package com.example.cs_office.Service;

import com.example.cs_office.Model.DateMasters;
import com.example.cs_office.Repository.DateMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DateMasterService {

    private final DateMasterRepository dateMasterRepository;

    @Autowired
    public DateMasterService(DateMasterRepository dateMasterRepository) {
        this.dateMasterRepository = dateMasterRepository;
    }

    public List<DateMasters> getDateMaster() {

        return dateMasterRepository.findAll();
    }

    public List<DateMasters> getDateMasterByStatus(boolean status) {

        return dateMasterRepository.findDateMasterByStatus(status);
    }

    public Optional<DateMasters> getDateMasterById(int Id) {
        Optional<DateMasters> dateMaster = dateMasterRepository.findById(Id);
        return dateMaster;
    }

    public void addNewdateMaster(DateMasters dateMasters) {
        Optional<DateMasters> dateMasterOptional =
                dateMasterRepository.findDateMasterById(dateMasters.getId());
        if (dateMasterOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        dateMasterRepository.save(dateMasters);
    }

    public void deleteDateMaster(int Id) {
        boolean exists = dateMasterRepository.existsById(Id);
        if (!exists) {
            throw new IllegalStateException("datemaster with id " + Id + " does not exists");
        }
        dateMasterRepository.deleteById(Id);
    }

    @Transactional
    public DateMasters updateDateMastersStatus(DateMasters dateMasters) {
        dateMasters.setStatus(false);
        return dateMasterRepository.save(dateMasters);
    }

    @Transactional
    public DateMasters updateDateMastersBlack(DateMasters dateMasters) {
        dateMasters.setStatus(true);
        return dateMasterRepository.save(dateMasters);
    }

    @Transactional
    public DateMasters updateDateMaster(DateMasters dateMasters, int datemasterId){
        DateMasters dateMasters1 = this.dateMasterRepository.getOne(datemasterId);
        BeanUtils.copyProperties(dateMasters, dateMasters1);
        return dateMasterRepository.saveAndFlush(dateMasters1);
    }

}
