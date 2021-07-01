package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

//    @Autowired
//    private JavaMailSender mailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
//        mailSender.send(email);
    }

}
