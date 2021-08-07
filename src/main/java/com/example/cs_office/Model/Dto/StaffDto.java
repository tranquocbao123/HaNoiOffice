package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class StaffDto {

    private String codeStaff;
    private String fullName;
    private boolean gender;
    private java.sql.Date birthDay;
    private String phoneNumber;
    private String address;
    private String email;
    private String userName;
    private String password;
    private String queQuan;
    private String hktt;
    private String description;
    private java.util.Date createDate = new java.util.Date();
    private boolean status = true;
    private Role role;
    private Branch branch;
}
