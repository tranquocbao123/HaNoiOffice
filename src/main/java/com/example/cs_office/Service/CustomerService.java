package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {

        return customerRepository.findAll();
    }

    public List<Customer> getCustomerByStatus(boolean status) {

        return customerRepository.findCustomerByStatus(status);
    }

    public Optional<Customer> getById(int userId) {
        Optional<Customer> customer = customerRepository.findById(userId);
        return customer;
    }

    public List<Customer> getCustomerByName(String firstname, String lastName) {
        List<Customer> customer = customerRepository.findCustomerByName(firstname, lastName);
        return customer;
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional =
                customerRepository.findCustomerById(customer.getId());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(int customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + " does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public Customer updateCustomerStatus(Customer customer) {
        customer.setStatus(false);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomerBlack(Customer customer) {
        customer.setStatus(true);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Customer customer, int customerId) {
        Customer customer1 = this.customerRepository.getOne(customerId);
        BeanUtils.copyProperties(customer, customer1);
        return customerRepository.saveAndFlush(customer1);
    }

}
