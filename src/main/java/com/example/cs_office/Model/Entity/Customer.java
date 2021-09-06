package com.example.cs_office.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "First name may not be null")
    private String firstName;
    @NotNull(message = "Last name may not be null")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Address may not be null")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    private String address;
    @NotBlank(message = "Phone number may not be blank")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 characters")
    private String phoneNumber;
    @NotNull(message = "Gender may not be null")
    private boolean gender;
    @NotNull(message = "Birthday may not be null")
    private java.sql.Date birthDay;
    @NotBlank(message = "Password may not be empty")
    private String password;
    private Date createDate = new Date();
    private boolean status = true;

    @JsonIgnore
    @OneToMany (mappedBy = "customer",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Orders> orders;

}
