package com.example.cs_office.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "create_Date")
    private Date createDate;
    private boolean status;
    @OneToMany
    private Set<Staff> staff;

    public Role(String name, Date createDate, boolean status) {
        this.name = name;
        this.createDate = createDate;
        this.status = status;
    }
}
