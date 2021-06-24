package com.example.cs_office.Service;

import com.example.cs_office.Model.Scheduledetail;
import com.example.cs_office.Model.ServiceDetail;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Repository.ServiceDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceDetailService {
    @Autowired
    private final ServiceDetailRepository serviceDetailRepository;

    public ServiceDetailService(ServiceDetailRepository serviceDetailRepository) {
        this.serviceDetailRepository = serviceDetailRepository;
    }
    public List<ServiceDetail> getServiceDetail() {

        return serviceDetailRepository.findAll();
    }

    public List<ServiceDetail> getServiceDetailByStatus(boolean status) {

        return serviceDetailRepository.findServiceDetailByStatus(status);
    }

    public Optional<ServiceDetail> getServiceDetailById(int serviceDetailId) {
        Optional<ServiceDetail> serviceDetail = serviceDetailRepository.findById(serviceDetailId);
        return serviceDetail;
    }

    public void addNewServiceDetail(ServiceDetail serviceDetail) {
        Optional<ServiceDetail> serviceDetailOptional =
                serviceDetailRepository.findServiceDetailById(serviceDetail.getId());
        if (serviceDetailOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        serviceDetailRepository.save(serviceDetail);
    }

    public void deleteServiceDetail(int serviceDetailId) {
        boolean exists = serviceDetailRepository.existsById(serviceDetailId);
        if (!exists) {
            throw new IllegalStateException("service detail with id " + serviceDetailId + " does not exists");
        }
        serviceDetailRepository.deleteById(serviceDetailId);
    }

    @Transactional
    public ServiceDetail updateServiceDetail(ServiceDetail serviceDetail, int serviceDetailId){
        ServiceDetail serviceDetail11 = this.serviceDetailRepository.getOne(serviceDetailId);
        BeanUtils.copyProperties(serviceDetail,serviceDetail11);
        return serviceDetailRepository.saveAndFlush(serviceDetail11);
    }
}
