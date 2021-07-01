package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Service.CustomerService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.CUSTOMER)
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //list customer
    @GetMapping(PathResources.FIND_ALL)
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    //list customer by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<Customer> getCustomerByStatusFalse() {
        return customerService.getCustomerByStatus(false);
    }

    //list customer by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<Customer> getCustomerByStatusTrue() {
        return customerService.getCustomerByStatus(true);
    }

    //search customer by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<Customer> getById(
            @PathVariable("id") int customerId) {
        return customerService.getById(customerId);
    }

    //search customer by name
    @GetMapping(path = PathResources.FIND_BY_FLNAME)
    public List<Customer> getCustomerByName(
            @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        return customerService.getCustomerByName(firstname, lastname);
    }

    //delete customer by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteCustomer(
            @PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
    }

    //update customer by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateCustomerStatus(
            @RequestBody Customer customer
    ) {
        customerService.updateCustomerStatus(customer);
    }

    //update customer black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateCustomerBlack(
            @RequestBody Customer customer
    ) {
        customerService.updateCustomerBlack(customer);
    }

    //update customer by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public Customer updateCustomer
    (@RequestBody Customer customer,
     @PathVariable("id") int customerId) {
        return customerService.updateCustomer(customer, customerId);
    }

}
