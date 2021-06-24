package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "datemasters")
public class DateMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "create_Date")
    private Date createDate = new Date();
    @Column(name = "date1")
    private Date date1;
    @Column(name = "description")
    private String desc;
    @Column(name = "status")
    private boolean status = true;

    public DateMaster(Date createDate, Date date1, String desc, boolean status) {
        this.createDate = createDate;
        this.date1 = date1;
        this.desc = desc;
        this.status = status;
    }
}
