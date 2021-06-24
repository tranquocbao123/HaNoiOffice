package com.example.cs_office.Controller;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.TypeRoom;
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

    // insert customer
    @PostMapping
    public void insertCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    //search customer by id
    @GetMapping(path = "{customerId}")
    public Optional<Customer> getById(
            @PathVariable("customerId") int customerId) {
        return customerService.getById(customerId);
    }
    //search customer by Email
    @GetMapping(path = "/customeremail/{email}")
    public List<Customer> searchName(@PathVariable("email")String email) {
        return  customerService.getCustomerByName(email);
    }

    // search customer by addres
    @GetMapping(path = "/customeraddress/{address}")
    public List<Customer> searchNameAddreess(@PathVariable("address")String address) {
        return  customerService.getCustomerByAddress(address);
    }
    // sear customer by first_name
    @GetMapping(path = "/customerfirstname/{firstname}")
    public List<Customer> searchNamefirstname(@PathVariable("firstname")String firstname) {
        return  customerService.getCustomerfirstname(firstname);
    }

    //delete customer by id
    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }

//    //update customer by id
//    @PutMapping
//    public Customer updateCustomer(
//            @RequestBody Customer customer
//    ) {
//      return customerService.updateCustomer(customer);
//    }
//    //update customer black by id
//    @PutMapping("/black")
//    public Customer updateCustomerBlack(
//            @RequestBody Customer customer
//    ) {
//        return customerService.updateCustomerBlack(customer);
//    }

    //update customer by id
    @PutMapping(path = "/{customerId}")
    public Customer updateCustomer
    (@RequestBody Customer customer,
     @PathVariable("customerId") int id) {
        return customerService.updateCustomer(customer, id);
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
