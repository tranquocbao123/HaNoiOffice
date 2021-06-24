package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String email;
    private String address;
    @Column(name = "createDate")
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

    public Staff(String userName, String password, String phoneNumber, String email, String address, Date createDate, boolean status, Branch branch, Role role) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.branch = branch;
        this.role = role;
    }

}
