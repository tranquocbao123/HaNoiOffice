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
    @ManyToOne
    @JoinColumn(name = "id_Branch")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "id_Role")
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
