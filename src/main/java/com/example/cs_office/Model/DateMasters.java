package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DateMasters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate = new Date();
    private Date date1;
    private String description;
    private boolean status = true;

    public DateMasters(Date createDate, Date date1, String description, boolean status) {
        this.createDate = createDate;
        this.date1 = date1;
        this.description = description;
        this.status = status;
    }
}
