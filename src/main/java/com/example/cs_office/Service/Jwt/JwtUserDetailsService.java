package com.example.cs_office.Service.Jwt;

import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findCustomerByEmail(email);
		if (customer == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
				new ArrayList<>());
	}

	public Customer save(Customer customer) {
		Customer newUser = new Customer();
		newUser.setEmail(customer.getEmail());
		newUser.setPassword(bcryptEncoder.encode(customer.getPassword()));
		return customerRepository.save(newUser);
	}
}