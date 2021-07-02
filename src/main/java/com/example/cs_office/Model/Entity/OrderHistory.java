package com.example.cs_office.Model.Entity;

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
    private String roomOld;
    private String startTimeOld;
    private String endTimeOld;
    private Date date;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idOrderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail2;
}
