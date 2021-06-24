package com.example.cs_office.Service;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.DateMaster;
import com.example.cs_office.Repository.DateMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DateMasterService {

    private final DateMasterRepository dateMasterRepository;

    @Autowired
    public DateMasterService(DateMasterRepository dateMasterRepository) {
        this.dateMasterRepository = dateMasterRepository;
    }

    public List<DateMaster> getDateMaster() {

        return dateMasterRepository.findAll();
    }

    public List<DateMaster> getDateMasterByStatus(boolean status) {

        return dateMasterRepository.findDateMasterByStatus(status);
    }

    public Optional<DateMaster> getById(int Id) {
        Optional<DateMaster> dateMaster = dateMasterRepository.findById(Id);
        return dateMaster;
    }

    public void addNewdateMaster(DateMaster dateMaster) {
        Optional<DateMaster> dateMasterOptional =
                dateMasterRepository.findDateMasterById(dateMaster.getId());
        if (dateMasterOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        dateMasterRepository.save(dateMaster);
    }

    public void deleteDateMaster(int Id) {
        boolean exists = dateMasterRepository.existsById(Id);
        if (!exists) {
            throw new IllegalStateException("datemaster with id " + Id + " does not exists");
        }
        dateMasterRepository.deleteById(Id);
    }

    @Transactional
    public void updateDateMaster(DateMaster dateMaster) {
        dateMaster.setStatus(false);
        dateMasterRepository.save(dateMaster);
    }

    @Transactional
    public void updateDateMasterBlack(DateMaster dateMaster) {
        dateMaster.setStatus(true);
        dateMasterRepository.save(dateMaster);
    }

}
