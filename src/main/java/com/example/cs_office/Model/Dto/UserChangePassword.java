package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserChangePassword {

    private String email;
    private String passwordOld;
    private String passwordNew;

}
