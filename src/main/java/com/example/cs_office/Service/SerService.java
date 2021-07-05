package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Service;
import com.example.cs_office.Repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
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

    public List<Service> getServiceByName(String serviceName) {
        List<Service> services = serviceRepository.findServiceByName(serviceName);
        return services;
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

    @Transactional
    public Service updateServiceStatus(Service service) {
        service.setStatus(false);
        return serviceRepository.save(service);
    }

    @Transactional
    public Service updateServiceBlack(Service service) {
        service.setStatus(true);
        return serviceRepository.save(service);
    }

    @Transactional
    public Service updateService(Service service, int serviceId){
        Service service1 = this.serviceRepository.getOne(serviceId);
        BeanUtils.copyProperties(service,service1);
        return serviceRepository.saveAndFlush(service1);
    }
}
