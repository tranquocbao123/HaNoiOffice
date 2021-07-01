package com.example.cs_office.Service;

import com.example.cs_office.Model.Dto.UserChangePassword;
import com.example.cs_office.Model.Dto.UserLogin;
import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

    public Customer findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            log.info("Email not exist!");
            return null;
        } else {
            SimpleMailMessage passWordSendEmail = new SimpleMailMessage();
            passWordSendEmail.setFrom("baotqph09487@fpt.edu.vn");
            passWordSendEmail.setTo(customer.getEmail());
            passWordSendEmail.setSubject("Your Password ");
            passWordSendEmail.setText("Your Password is : " + customer.getPassword());

            return customerRepository.findByEmail(email);
        }
    }

    public void changePassword(UserChangePassword user) {
        Customer customer = findByEmail(user.getEmail());
        if (customer == null) {
            log.info("Email not exits!");
            return;
        } else {
            if (!customer.getPassword().equalsIgnoreCase(user.getPasswordOld())) {
                log.info("Password false");
            } else {
                customer.setPassword(user.getPasswordNew());
                customerRepository.save(customer);
            }
        }
    }

    public void login(UserLogin userLogin) {
        try {
            Customer customer = new Customer();
            if (findByEmail(userLogin.getEmail()).getEmail() == null) {
                customer = findByEmail(userLogin.getEmail());
                System.out.println("customer " + customer.getCodeCustomer());
                log.info("Email not exits!");
                return;
            } else {
                System.out.println("pass db " + customer.getPassword());
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }

    }

}
