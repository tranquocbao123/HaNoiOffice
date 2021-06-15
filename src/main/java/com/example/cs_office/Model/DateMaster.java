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
@Table(name = "Date_Master")
public class DateMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "desc")
    private String desc;
    @Column(name = "status")
    private boolean status;
    @Column(name = "create_Date")
    private Date createDate;

    public DateMaster(Date date, String desc, boolean status, Date createDate) {
        this.date = date;
        this.desc = desc;
        this.status = status;
        this.createDate = createDate;
    }
}
