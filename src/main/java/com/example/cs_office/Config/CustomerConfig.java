package com.example.cs_office.Config;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

//@Configuration
public class CustomerConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository repository){
//        return args -> {
//            Customer customer = new Customer(
//                     "bao@gmail.com"
//            );
//            Customer customer2 = new Customer(
//                    4,"0969145287", "vannh@gmail.com", "bao123", "Bao", "Tran","80 Xuan Phuong", new Date(), true
//            );

//            repository.findCustomerById(4);
//            repository.saveAll(List.of(customer2));
//            repository.saveAndFlush(customer2);
//            repository.deleteById(2);
//            repository.findCustomerByEmail(customer.getEmail());
//        };
//    }
}
