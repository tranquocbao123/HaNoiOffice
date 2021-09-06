package com.example.cs_office.Model.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int id;
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
    private int branch;
    private int role;
}
