package com.example.cs_office.Model.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePassword {
    private String email;
    private String passwordOld;
    private String passwordNew;
}
