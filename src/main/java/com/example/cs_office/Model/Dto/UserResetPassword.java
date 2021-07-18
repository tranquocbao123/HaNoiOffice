package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResetPassword {
    private String email;
    private String password;
}
