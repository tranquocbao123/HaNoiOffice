package com.example.cs_office.Service;

import com.example.cs_office.Mapper.CustomerMapperToDto;
import com.example.cs_office.Mapper.StaffMapperToDto;
import com.example.cs_office.Model.Dto.CustomerDto;
import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Model.Entity.Staff;
import com.example.cs_office.Repository.CustomerRepository;
import com.example.cs_office.Repository.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username);
        Staff staff = staffRepository.findStaffByEmail(username);
        if (customer == null && staff == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        if (customer != null & staff == null) {
            return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                    new ArrayList<>());
        }
        if (customer == null & staff != null) {
            return new org.springframework.security.core.userdetails.User(staff.getEmail(), staff.getPassword(),
                    new ArrayList<>());
        }
        return new org.springframework.security.core.userdetails.User(staff.getEmail(), staff.getPassword(),
                new ArrayList<>());
    }

    @Transactional
    public boolean cutomerChangePassword(int customerId, String passwordOld, String passwordNew) {
        if (passwordOld.equalsIgnoreCase(passwordNew)) {
            log.info("Trung nhau");
            return false;
        } else {
            Optional<Customer> customer = customerRepository.findCustomerById(customerId);
            Customer customer1 = this.customerRepository.getOne(customerId);
            BeanUtils.copyProperties(customer, customer1);
            customer1.setPassword(bcryptEncoder.encode(passwordNew));
            customerRepository.saveAndFlush(customer1);
            return true;
        }
    }

    @Transactional
    public boolean staffChangePassword(int staffId, String passwordOld, String passwordNew) {
        if (passwordOld.equalsIgnoreCase(passwordNew)) {
            log.info("Trung nhau");
            return false;
        } else {
            Optional<Staff> staff = staffRepository.findStaffById(staffId);
            Staff staff1 = this.staffRepository.getOne(staffId);
            BeanUtils.copyProperties(staff, staff1);
            staff1.setPassword(bcryptEncoder.encode(passwordNew));
            staffRepository.saveAndFlush(staff1);
            return true;
        }
    }

    public boolean logout() {
        return true;
    }

    public boolean saveCustomer(CustomerDto user) {
        if (customerRepository.findCustomerByEmail(user.getEmail()) != null || staffRepository.findStaffByEmail(user.getEmail())!= null) {
            log.info("Email exits ");
            return false;
        } else {
            Customer newUser = new Customer();
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            CustomerMapperToDto customerMapperToDto = new CustomerMapperToDto();
            newUser = customerMapperToDto.customerMapperToDto(user);
            customerRepository.save(newUser);
            return true;
        }
    }

    public boolean saveStaff(StaffDto user) {
        if (staffRepository.findStaffByEmail(user.getEmail()) != null || customerRepository.findCustomerByEmail(user.getEmail()) != null ) {
            log.info("Email exits ");
            return false;
        } else {
            Staff newUser = new Staff();
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            StaffMapperToDto staffMapperToDto = new StaffMapperToDto();
            newUser = staffMapperToDto.staffMapperToDto(user);
            staffRepository.save(newUser);
            return true;
        }
    }
}
