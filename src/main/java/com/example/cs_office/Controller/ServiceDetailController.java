package com.example.cs_office.Controller;


import com.example.cs_office.Model.ServiceDetail;
import com.example.cs_office.Service.ServiceDetailService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.SERVICEDETAIL)
@CrossOrigin("*")
public class ServiceDetailController {
    @Autowired
    private final ServiceDetailService serviceDetailService;

    public ServiceDetailController(ServiceDetailService serviceDetailService) {
        this.serviceDetailService = serviceDetailService;
    }

    //list service detail
    @GetMapping(PathResources.FIND_ALL)
    public List<ServiceDetail> getServiceDetail() {
        return serviceDetailService.getServiceDetail();
    }

    //list service detail by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<ServiceDetail> getServiceDetailByStatusFalse() {
        return serviceDetailService.getServiceDetailByStatus(false);
    }

    //list service detail by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<ServiceDetail> getServiceDetailByStatusTrue() {
        return serviceDetailService.getServiceDetailByStatus(true);
    }

    // insert service detail
    @PostMapping(PathResources.SAVE)
    public void insertServiceDetail(@RequestBody ServiceDetail serviceDetail) {
        serviceDetailService.addNewServiceDetail(serviceDetail);
    }

    //search service detail by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<ServiceDetail> getServiceDetailById(
            @PathVariable("id") int servicedetailId) {
        return serviceDetailService.getServiceDetailById(servicedetailId);
    }

    //delete service detail by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteServiceDetail(
            @PathVariable("id") int servicedetailId) {
        serviceDetailService.deleteServiceDetail(servicedetailId);
    }

    //update service detail by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateServiceDetailStatus(
            @RequestBody ServiceDetail serviceDetail
    ) {
        serviceDetailService.updateServiceDetailStatus(serviceDetail);
    }

    //update service black by id
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateServiceDetailBlack(
            @RequestBody ServiceDetail serviceDetail
    ) {
        serviceDetailService.updateServiceDetailBlack(serviceDetail);
    }

    //update service detail by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public ServiceDetail updateServiceDetail
    (@RequestBody ServiceDetail serviceDetail,
     @PathVariable("id") int serviceDetailId) {
        return serviceDetailService.updateServiceDetail(serviceDetail, serviceDetailId);
    }

}
