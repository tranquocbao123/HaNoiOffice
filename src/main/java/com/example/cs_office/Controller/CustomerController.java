package com.example.cs_office.Controller;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    //delete customer by id
    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }

    //update customer by id
    @PutMapping
    public Customer updateCustomer(
            @RequestBody Customer customer
    ) {
        return customerService.updateCustomer(customer);
    }

    //update customer black by id
    @PutMapping("/black")
    public Customer updateCustomerBlack(
            @RequestBody Customer customer
    ) {
        return customerService.updateCustomerBlack(customer);
    }

    //login customer by id
    @PostMapping("login")
    public Customer loginCustomer(Customer customer) {
        return customerService.loginCustomer(customer);
    }

    //forgot password customer by id
    @GetMapping("/forgot")
    public void forgotPassword(
            @RequestParam(required = false) String email
    ) {
        customerService.forgotPassword(email);
    }
}
