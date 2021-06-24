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
    @Column(name = "start_Time")
    private String startTime;
    @Column(name = "end_Time")
    private String endTime;
    @Column(name = "create_Date")
    private Date createDate = new Date();
    private boolean status = true;
    @Column(name = "edit_Date")
    private Date editdate;

    @ManyToOne
    @JoinColumn(name = "id_Order_Detail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail1;

    @ManyToOne
    @JoinColumn(name = "id_Shedule")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Schedule schedule ;
}
