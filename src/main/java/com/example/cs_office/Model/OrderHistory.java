package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderhistory")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_Old")
    private String roomOld;
    @Column(name = "start_Time_Old")
    private String startTimeOld;
    @Column(name = "end_Time_Old")
    private String endTimeOld;
    private Date date;
    @Column(name = "create_Date")
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "id_Order_Detail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail2;
}
