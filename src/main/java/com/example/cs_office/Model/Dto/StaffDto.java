package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class StaffDto {

    private String codeStaff;
    private String fullName;
    private boolean gender;
    private Date birthDay;
    private String phoneNumber;
    private String address;
    private String email;
    private String userName;
    private String password;
    private String queQuan;
    private String hktt;
    private String image;
    private String description;
    private Date createDate = new Date();
    private boolean status = true;
    private Branch branch;
    private Role role;
}
