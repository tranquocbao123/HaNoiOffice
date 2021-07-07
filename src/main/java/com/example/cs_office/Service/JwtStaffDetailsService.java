package com.example.cs_office.Service;

import com.example.cs_office.Mapper.StaffMapperToDto;
import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Entity.Staff;
import com.example.cs_office.Repository.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("JwtStaffDetailsService")
@Slf4j
public class JwtStaffDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findStaffByEmail(username);
        if (staff == null) {
            throw new UsernameNotFoundException("Staff not found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(staff.getEmail(), staff.getPassword(),
                new ArrayList<>());
    }

    public Staff save(StaffDto staffDto) {
        StaffMapperToDto staffMapperToDto = new StaffMapperToDto();
        staffDto.setPassword(bcryptEncoder.encode(staffDto.getPassword()));
        Staff staff = staffMapperToDto.staffMapperToDto(staffDto);
        return staffRepository.save(staff);
    }
}
