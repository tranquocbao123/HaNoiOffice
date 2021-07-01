package com.example.cs_office.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "datemasters")
public class DateMasters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateMater;
    private String event;
    private String description;
    private Date createDate = new Date();
    private boolean status = true;

    public DateMasters(Date dateMater, String event, String description, Date createDate, boolean status) {
        this.dateMater = dateMater;
        this.event = event;
        this.description = description;
        this.createDate = createDate;
        this.status = status;
    }
}
