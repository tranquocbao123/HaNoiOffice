package com.example.cs_office.Service;

import com.example.cs_office.Mapper.CustomerMapperToDto;
import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.Dto.CustomerDto;
import com.example.cs_office.Repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("customer not found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                new ArrayList<>());
    }

    public Customer save(CustomerDto customerDto) {
        CustomerMapperToDto customerMapperToDto = new CustomerMapperToDto();
        customerDto.setPassword(bcryptEncoder.encode(customerDto.getPassword()));
        Customer customer = customerMapperToDto.customerMapperToDto(customerDto);
        return customerRepository.save(customer);
    }
}