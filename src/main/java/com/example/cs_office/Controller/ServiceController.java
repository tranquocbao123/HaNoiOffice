package com.example.cs_office.Controller;


import com.example.cs_office.Model.Entity.Service;
import com.example.cs_office.Service.SerService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.SERVICE)
@CrossOrigin("*")
public class ServiceController {

    @Autowired
    private final SerService serService;

    public ServiceController(SerService serService) {
        this.serService = serService;
    }

    //list service
    @GetMapping(PathResources.FIND_ALL)
    public List<Service> getService() {
        return serService.getService();
    }

    //list service by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Service> getServiceByStatusFalse() {
        return serService.getServiceByStatus(false);
    }

    //list service by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Service> getServiceByStatusTrue() {
        return serService.getServiceByStatus(true);
    }

    // insert service
    @PostMapping(PathResources.SAVE)
    public void insertService(@RequestBody Service service) {
        serService.addNewService(service);
    }

    //search service by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Service> getServiceById(
            @PathVariable("id") int serviceId) {
        return serService.getServiceById(serviceId);
    }

    //search service by name
    @GetMapping(path = PathResources.FIND_BY_NAME)
    public List<Service> getServiceByName(
            @PathVariable("name") String serviceName) {
        return serService.getServiceByName(serviceName);
    }

    //delete service by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteService(
            @PathVariable("id") int serviceId) {
        serService.deleteService(serviceId);
    }

    //update service by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateServiceStatus(
            @RequestBody Service service
    ) {
        serService.updateServiceStatus(service);
    }

    //update service black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateServiceBlack(
            @RequestBody Service service
    ) {
        serService.updateServiceBlack(service);
    }

    //update service by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Service updateService
    (@RequestBody Service service,
     @PathVariable("id") int serviceId) {
        return serService.updateService(service, serviceId);
    }

}
