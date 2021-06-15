package com.example.cs_office.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "phone_Number")
    private String phoneNumber;
    private String email;
    private String address;
    @Column(name = "create_Date")
    private Date createDate = new Date();
    private boolean status = true;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Branch",referencedColumnName = "id")
    private Branch branch;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Role", referencedColumnName = "id")
    private Role role;

    public Staff(String userName, String password, String phoneNumber, String email, String address, Branch branch, Role role) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.branch = branch;
        this.role = role;
    }
}
