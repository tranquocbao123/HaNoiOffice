package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.PriceService;
import com.example.cs_office.Service.PriceServiceService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.PRICESERVICE)
@CrossOrigin("*")
public class PriceServiceController {

    @Autowired
    private final PriceServiceService priceServiceService;

    public PriceServiceController(PriceServiceService priceServiceSercice) {
        this.priceServiceService = priceServiceSercice;
    }


    //list priceservice
    @GetMapping(PathResources.FIND_ALL)
    public List<PriceService> getPriceService() {
        return priceServiceService.getPriceService();
    }

    //list priceservice by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<PriceService> getServiceByStatusFalse() {
        return priceServiceService.getPriceServiceByStatus(false);
    }

    //list priceservice by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<PriceService> getServiceByStatusTrue() {
        return priceServiceService.getPriceServiceByStatus(true);
    }

    // insert priceservice
    @PostMapping(PathResources.SAVE)
    public void insertService(@RequestBody PriceService priceService) {
        priceServiceService.addNewpriceService(priceService);
    }

    //search priceservice by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<PriceService> getServiceById(
            @PathVariable("id") int priceServiceId) {
        return priceServiceService.getpriceServiceById(priceServiceId);
    }

    //delete priceservice by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteService(
            @PathVariable("id") int priceServiceId) {
        priceServiceService.deletepriceService(priceServiceId);
    }

    //update price service by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updatePriceServiceStatus(
            @RequestBody PriceService priceService
    ) {
        priceServiceService.updatePriceServiceStatus(priceService);
    }

    //update price service black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updatePriceServiceBlack(
            @RequestBody PriceService priceService
    ) {
        priceServiceService.updatePriceServiceBlack(priceService);
    }

    //update priceservice by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public PriceService updateService
    (@RequestBody PriceService priceService,
     @PathVariable("id") int priceServiceId) {
        return priceServiceService.updatePriceServiceService(priceService, priceServiceId);
    }

}
