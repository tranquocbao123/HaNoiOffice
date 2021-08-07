package com.example.cs_office.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Code branch may not be blank")
    private String codeBranch;
    @NotBlank(message = "Name branch may not be blank")
    private String name;
    @NotBlank(message = "Phone number may not be blank")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 characters")
    private String phoneNumber;
    @NotNull(message = "Address may not be null")
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    private String address;
    @NotBlank(message = "Branch manager may not be blank")
    private String branchManager;
    @NotBlank(message = "Phone number manager may not be blank")
    @Size(min = 10, max = 11, message = "Phone number manager must be between 10 and 11 characters")
    private String phoneNumberManager;
    @NotNull(message = "Description manager may not be null")
    private String description;
    private Date createDate = new Date();
    private boolean status = true;

    @JsonIgnore
    @OneToMany (mappedBy = "branch", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Staff> staff;

    @JsonIgnore
    @OneToMany (mappedBy = "branch1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Room> rooms;

}
