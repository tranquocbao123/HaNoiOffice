package com.example.cs_office.Service;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
    public Customer updateCustomer(Customer customer) {
        customer.setStatus(false);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomerBlack(Customer customer) {
        customer.setStatus(true);
        return customerRepository.save(customer);
    }

    @Transactional
    public String forgotPassword(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            return "Customer not exist";
        } else {
            return customer.getPassword();
        }
    }

    @Transactional
    public Customer loginCustomer(Customer customer) {
        Customer user = customerRepository.findCustomerByEmail(customer.getEmail());
        if (user == null) {
            throw new IllegalStateException("customer with id " + customer + " does not exists");
        } else {
            if (user.getPassword() != customer.getPassword()) {
                throw new IllegalStateException("pass customer with id " + customer + " does not exists");
            }else{
                return user;
            }
        }
    }
}