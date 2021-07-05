package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.PriceService;
import com.example.cs_office.Repository.PriceServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceService {

    @Autowired
    private  final PriceServiceRepository priceServiceRepository;

    public PriceServiceService(PriceServiceRepository priceServiceRepository) {
        this.priceServiceRepository = priceServiceRepository;
    }

    public List<PriceService> getPriceService() {
        return priceServiceRepository.findAll();
    }

    public List<PriceService> getPriceServiceByStatus(boolean status) {

        return priceServiceRepository.findPriceServiceByStatus(status);
    }

    public Optional<PriceService> getpriceServiceById(int priceServiceId) {
        Optional<PriceService> priceService = priceServiceRepository.findById(priceServiceId);
        return priceService;
    }

    public void addNewpriceService(PriceService priceService) {
        Optional<PriceService> priceServiceOptional =
                priceServiceRepository.findPriceServiceById(priceService.getId());
        if (priceServiceOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        priceServiceRepository.save(priceService);
    }

    public void deletepriceService(int priceServiceId) {
        boolean exists = priceServiceRepository.existsById(priceServiceId);
        if (!exists) {
            throw new IllegalStateException("price service with id " + priceServiceId + " does not exists");
        }
        priceServiceRepository.deleteById(priceServiceId);
    }

    @Transactional
    public PriceService updatePriceServiceStatus(PriceService priceService) {
        priceService.setStatus(false);
        return priceServiceRepository.save(priceService);
    }

    @Transactional
    public PriceService updatePriceServiceBlack(PriceService priceService) {
        priceService.setStatus(true);
        return priceServiceRepository.save(priceService);
    }

    @Transactional
    public PriceService updatePriceServiceService(PriceService priceService, int priceserserId){
        PriceService priceService1 = this.priceServiceRepository.getOne(priceserserId);
        BeanUtils.copyProperties(priceService,priceService1);
        return priceServiceRepository.saveAndFlush(priceService1);
    }
}
