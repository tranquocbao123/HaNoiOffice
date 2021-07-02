package com.example.cs_office.Service;

import com.example.cs_office.Model.Entity.Customer;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;

public interface EmailService {

    public Customer findCustomerByEmail(String email);

    public void save(Customer customer);

    public void sendEmail(SimpleMailMessage email);

}
