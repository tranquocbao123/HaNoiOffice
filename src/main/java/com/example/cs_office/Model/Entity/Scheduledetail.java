package com.example.cs_office.Model.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Date may not be blank")
    private Date date;
    @NotBlank(message = "Start time may not be blank")
    private String startTime;
    @NotBlank(message = "End time may not be blank")
    private String endTime;
    private Date createDate = new Date();
    private boolean status = true;
    private Date editdate;

    @ManyToOne
    @JoinColumn(name = "idOrderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail1;

    @ManyToOne
    @JoinColumn(name = "idShedule")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule;
}
