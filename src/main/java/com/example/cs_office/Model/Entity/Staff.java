package com.example.cs_office.Model.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Code staff may not be blank")
    private String codeStaff;
    @NotBlank(message = "Full name may not be blank")
    private String fullName;
    @NotBlank(message = "Gender may not be blank")
    private boolean gender;
    @NotBlank(message = "Birthday may not be blank")
    private Date birthDay;
    @NotBlank(message = "Phone number may not be blank")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 characters")
    private String phoneNumber;
    @NotNull(message = "Address may not be null")
    @Size(min = 5, max = 200, message = "Address must be between 10 and 200 characters")
    private String address;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "User name may not be blank")
    private String userName;
    @NotBlank(message = "Password may not be empty")
    private String password;
    @NotBlank(message = "Que quan may not be blank")
    private String queQuan;
    @NotBlank(message = "Hktt may not be blank")
    private String hktt;
    @NotBlank(message = "Image may not be blank")
    private String image;
    @NotNull(message = "Description may not be null")
    private String description;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idBranch")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "idRole")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

}
