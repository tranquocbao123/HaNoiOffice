package com.example.cs_office.Model.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Room old may not be blank")
    private String roomOld;
    @NotBlank(message = "Start time old may not be blank")
    private String startTimeOld;
    @NotBlank(message = "End time old may not be blank")
    private String endTimeOld;
    @NotBlank(message = "Date may not be blank")
    private Date date;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idOrderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail2;
}
