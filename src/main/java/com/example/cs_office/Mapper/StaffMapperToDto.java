package com.example.cs_office.Mapper;

import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Entity.Staff;

import java.util.Date;

public class StaffMapperToDto {

    public Staff staffMapperToDto(StaffDto staffDto){
        Staff staff = new Staff();
        staff.setCodeStaff(staffDto.getCodeStaff());
        staff.setFullName(staffDto.getFullName());
        staff.setGender(staffDto.isGender());
        staff.setBirthDay(staffDto.getBirthDay());
        staff.setPhoneNumber(staffDto.getPhoneNumber());
        staff.setAddress(staffDto.getAddress());
        staff.setEmail(staffDto.getEmail());
        staff.setUserName(staffDto.getUserName());
        staff.setPassword(staffDto.getPassword());
        staff.setQueQuan(staffDto.getQueQuan());
        staff.setHktt(staffDto.getHktt());
        staff.setDescription(staffDto.getDescription());
        staff.setCreateDate(new Date());
        staff.setStatus(true);
        staff.setBranch(staffDto.getBranch());
        staff.setRole(staffDto.getRole());
        return staff;
    }
}
