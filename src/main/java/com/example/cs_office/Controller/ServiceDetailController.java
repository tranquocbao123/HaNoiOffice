package com.example.cs_office.Controller;


import com.example.cs_office.Model.Scheduledetail;
import com.example.cs_office.Model.ServiceDetail;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicedetail")
@CrossOrigin("*")
public class ServiceDetailController {
    @Autowired
    private final ServiceDetailService serviceDetailService;

    public ServiceDetailController(ServiceDetailService serviceDetailService) {
        this.serviceDetailService = serviceDetailService;
    }

    //list service detail
    @GetMapping()
    public List<ServiceDetail> getServiceDetail() {
        return serviceDetailService.getServiceDetail();
    }

    //list service detail by status == fasle
    @GetMapping("/false")
    public List<ServiceDetail> getServiceDetailByStatusFalse() {
        return serviceDetailService.getServiceDetailByStatus(false);
    }

    //list service detail by status == true
    @GetMapping("/true")
    public List<ServiceDetail> getServiceDetailByStatusTrue() {
        return serviceDetailService.getServiceDetailByStatus(true);
    }

    // insert service detail
    @PostMapping
    public void insertServiceDetail(@RequestBody ServiceDetail serviceDetail) {
        serviceDetailService.addNewServiceDetail(serviceDetail);
    }

    //search service detail by id
    @GetMapping(path = "{servicedetailId}")
    public Optional<ServiceDetail> getServiceDetailById(
            @PathVariable("servicedetailId") int servicedetailId) {
        return serviceDetailService.getServiceDetailById(servicedetailId);
    }

    //delete service detail by id
    @DeleteMapping(path = "{servicedetailId}")
    public void deleteServiceDetail(
            @PathVariable("servicedetailId") int servicedetailId) {
        serviceDetailService.deleteServiceDetail(servicedetailId);
    }
    //update service detail by id
    @PutMapping(path = "/{serviceDetailId}")
    public ServiceDetail updateServiceDetail
    (@RequestBody ServiceDetail serviceDetail,
     @PathVariable("serviceDetailId") int id) {
        return serviceDetailService.updateServiceDetail(serviceDetail, id);
    }

}
