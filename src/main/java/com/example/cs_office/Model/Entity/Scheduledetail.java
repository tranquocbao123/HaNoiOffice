package com.example.cs_office.Model.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scheduledetail")
public class Scheduledetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate = new Date();
    private java.sql.Date datePresent;
    private boolean acceptance;
    private boolean status = true;
    private boolean checkinout = true;

    @ManyToOne
    @JoinColumn(name = "idSchedule")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "idShift")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Shift shift;
}
