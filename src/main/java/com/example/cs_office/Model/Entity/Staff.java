package com.example.cs_office.Model.Entity;

import lombok.*;

import javax.persistence.*;
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
