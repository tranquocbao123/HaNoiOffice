package com.example.cs_office.Service;

import com.example.cs_office.Model.Service;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class SerService {
    @Autowired
    private final ServiceRepository serviceRepository;

    public SerService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getService() {
        return serviceRepository.findAll();
    }

    public List<Service> getServiceByStatus(boolean status) {

        return serviceRepository.findServiceByStatus(status);
    }

    public Optional<Service> getServiceById(int serviceId) {
        Optional<Service> service = serviceRepository.findById(serviceId);
        return service;
    }

    public void addNewService(Service service) {
        Optional<Service> serviceOptional =
                serviceRepository.findServiceById(service.getId());
        if (serviceOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        serviceRepository.save(service);
    }

    public void deleteService(int serviceId) {
        boolean exists = serviceRepository.existsById(serviceId);
        if (!exists) {
            throw new IllegalStateException("service with id " + serviceId + " does not exists");
        }
        serviceRepository.deleteById(serviceId);
    }

    public Service updateService(Service service, int serviceId){
        Service service1 = this.serviceRepository.getOne(serviceId);
        BeanUtils.copyProperties(service,service1);
        return serviceRepository.saveAndFlush(service1);
    }

    public List<Service> getServiceBy(String name) {
        return serviceRepository.findservice(name);
    }
}
