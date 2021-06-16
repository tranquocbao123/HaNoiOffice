package com.example.cs_office.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @Column(name = "create_Date")
    private Date createDate = new Date();
    private boolean status = true;
    @JsonIgnore
    @OneToMany (mappedBy = "branch", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Staff> staff;

    public Branch(String name, String address, Date createDate, boolean status, Collection<Staff> staff) {
        this.name = name;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.staff = staff;
    }

}
