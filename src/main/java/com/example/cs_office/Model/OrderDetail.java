package com.example.cs_office.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idorder")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders2;

    @ManyToOne
    @JoinColumn(name = "idRoom")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;

    @JsonIgnore
    @OneToMany (mappedBy = "orderDetail", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ServiceDetail> serviceDetails;

    @JsonIgnore
    @OneToMany (mappedBy = "orderDetail1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Scheduledetail> scheduledetails;

    @JsonIgnore
    @OneToMany (mappedBy = "orderDetail2", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderHistory> orderHistories;
}
