package com.example.cs_office.Service;

import com.example.cs_office.Mapper.CustomerMapperToDto;
import com.example.cs_office.Mapper.StaffMapperToDto;
import com.example.cs_office.Model.Dto.CustomerDto;
import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Model.Entity.Staff;
import com.example.cs_office.Repository.CustomerRepository;
import com.example.cs_office.Repository.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service()
@Slf4j
public class JwtDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findCustomerByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Customer not found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public Customer save(CustomerDto user) {
        Customer newUser = new Customer();
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        CustomerMapperToDto customerMapperToDto = new CustomerMapperToDto();
        newUser = customerMapperToDto.customerMapperToDto(user);
        return customerRepository.save(newUser);
    }
}
