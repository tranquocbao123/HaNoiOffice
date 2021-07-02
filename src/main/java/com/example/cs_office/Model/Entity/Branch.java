package com.example.cs_office.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    private String codeBranch;
    private String name;
    private String phoneNumber;
    private String address;
    private int numberStaff;
    private String branchManager;
    private String phoneNumberManager;
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

    public Branch(String name, String address, Date createDate, boolean status, Collection<Staff> staff) {
        this.name = name;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.staff = staff;
    }

}
