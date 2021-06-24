package com.example.cs_office.Controller;

import com.example.cs_office.Model.PriceService;
import com.example.cs_office.Model.Service;
import com.example.cs_office.Service.PriceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priceservice")
@CrossOrigin("*")
public class PriceServiceController {

    @Autowired
    private final PriceServiceService priceServiceService;

    public PriceServiceController(PriceServiceService priceServiceSercice) {
        this.priceServiceService = priceServiceSercice;
    }


    //list priceservice
    @GetMapping()
    public List<PriceService> getPriceService() {
        return priceServiceService.getPriceService();
    }

    //list priceservice by status == fasle
    @GetMapping("/false")
    public List<PriceService> getServiceByStatusFalse() {
        return priceServiceService.getPriceServiceByStatus(false);
    }

    //list priceservice by status == true
    @GetMapping("/true")
    public List<PriceService> getServiceByStatusTrue() {
        return priceServiceService.getPriceServiceByStatus(true);
    }

    // insert priceservice
    @PostMapping
    public void insertService(@RequestBody PriceService priceService) {
        priceServiceService.addNewpriceService(priceService);
    }

    //search priceservice by id
    @GetMapping(path = "{priceServiceId}")
    public Optional<PriceService> getServiceById(
            @PathVariable("priceServiceId") int priceServiceId) {
        return priceServiceService.getpriceServiceById(priceServiceId);
    }

    //delete priceservice by id
    @DeleteMapping(path = "{priceServiceId}")
    public void deleteService(
            @PathVariable("priceServiceId") int priceServiceId) {
        priceServiceService.deletepriceService(priceServiceId);
    }

    //update priceservice by id
    @PutMapping(path = "/{priceServiceId}")
    public PriceService updateService
    (@RequestBody PriceService priceService,
     @PathVariable("priceServiceId") int id) {
        return priceServiceService.updatePriceServiceService(priceService, id);
    }

}
