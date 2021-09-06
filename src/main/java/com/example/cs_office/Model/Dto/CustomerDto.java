package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean gender;
    private java.sql.Date birthDay;
    private String password;
    private Date createDate = new Date();
    private boolean status = true;

}
