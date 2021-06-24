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
    @Column(name = "roomOld")
    private String roomOld;
    @Column(name = "startTimeOld")
    private String startTimeOld;
    @Column(name = "endTimeOld")
    private String endTimeOld;
    private Date date;
    @Column(name = "createDate")
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idOrderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail2;
}
