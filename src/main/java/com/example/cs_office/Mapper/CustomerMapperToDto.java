package com.example.cs_office.Mapper;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Model.Dto.CustomerDto;

import java.util.Date;

public class CustomerMapperToDto {

    public Customer customerMapperToDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCodeCustomer(customerDto.getCodeCustomer());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setGender(customerDto.isGender());
        customer.setBirthDay(customerDto.getBirthDay());
        customer.setPassword(customerDto.getPassword());
        customer.setCreateDate(new Date());
        customer.setStatus(true);
        return customer;
    }

}
