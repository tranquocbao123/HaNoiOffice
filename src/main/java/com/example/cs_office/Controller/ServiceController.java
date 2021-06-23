package com.example.cs_office.Controller;


import com.example.cs_office.Model.Role;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Model.Service;
import com.example.cs_office.Service.SerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
@CrossOrigin("*")
public class ServiceController {

    @Autowired
    private final SerService serService;

    public ServiceController(SerService serService) {
        this.serService = serService;
    }

    //list service
    @GetMapping()
    public List<Service> getService() {
        return serService.getService();
    }

    //list service by status == fasle
    @GetMapping("/false")
    public List<Service> getServiceByStatusFalse() {
        return serService.getServiceByStatus(false);
    }

    //list service by status == true
    @GetMapping("/true")
    public List<Service> getServiceByStatusTrue() {
        return serService.getServiceByStatus(true);
    }

    // insert service
    @PostMapping
    public void insertService(@RequestBody Service service) {
        serService.addNewService(service);
    }

    //search service by id
    @GetMapping(path = "{serviceId}")
    public Optional<Service> getServiceById(
            @PathVariable("serviceId") int serviceId) {
        return serService.getServiceById(serviceId);
    }

    //search service by name
    @GetMapping(path = "searchname/{serviceName}")
    public List<Service> getServiceByName(
            @PathVariable("serviceName") String serviceName) {
        return serService.getServiceByName(serviceName);
    }

    //delete service by id
    @DeleteMapping(path = "{serviceId}")
    public void deleteService(
            @PathVariable("serviceId") int serviceId) {
        serService.deleteService(serviceId);
    }

    //update service by id
    @PutMapping(path = "/{serviceId}")
    public Service updateService
    (@RequestBody Service service,
     @PathVariable("serviceId") int id) {
        return serService.updateService(service, id);
    }
}
