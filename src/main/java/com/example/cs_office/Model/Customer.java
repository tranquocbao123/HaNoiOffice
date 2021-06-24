package com.example.cs_office.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String email;
    @Column(name = "passWord")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    private String address;
    @Column(name = "createDate")
    private Date createDate = new Date();
    private boolean status = true;

    @JsonIgnore
    @OneToMany (mappedBy = "customer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Orders> orders;

    public Customer(String phoneNumber, String email, String password, String firstName, String lastName, String address) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
