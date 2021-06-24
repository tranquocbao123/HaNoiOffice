package com.example.cs_office.Model;


import lombok.*;

import javax.persistence.*;
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
    private Date date;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "endTime")
    private String endTime;
    @Column(name = "createDate")
    private Date createDate = new Date();
    private boolean status = true;
    @Column(name = "editDate")
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
    private Schedule schedule ;
}
