package com.example.cs_office.Controller;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //list customer
    @GetMapping()
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    //list customer by status == fasle
    @GetMapping("/false")
    public List<Customer> getCustomerByStatusFalse() {
        return customerService.getCustomerByStatus(false);
    }

    //list customer by status == true
    @GetMapping("/true")
    public List<Customer> getCustomerByStatusTrue() {
        return customerService.getCustomerByStatus(true);
    }

    //search customer by id
    @GetMapping(path = "{customerId}")
    public Optional<Customer> getById(
            @PathVariable("customerId") int customerId) {
        return customerService.getById(customerId);
    }

    // insert customer
    @PostMapping
    public void insertCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    //search customer by name
    @GetMapping(path = "searchname/{firstname}/{lastname}")
    public List<Customer> getCustomerByName(
            @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        return customerService.getCustomerByName(firstname, lastname);
    }

    //delete customer by id
    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }

    //update customer by id
    @PutMapping(path = "/{customerId}")
    public Customer updateCustomer
    (@RequestBody Customer customer,
     @PathVariable("customerId") int id) {
        return customerService.updateCustomer(customer, id);
    }

}
