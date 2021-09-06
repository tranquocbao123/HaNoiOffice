package com.example.cs_office.Model.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdetail")
@NamedStoredProcedureQuery(name = "OrderDetail.getCountOrderDetailEntity",
procedureName = "count_orderdetail",parameters = {
@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate = new Date();
    private boolean status = true;
    private boolean acceptance;
    private boolean done = false;

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
    private List<Schedule> schedules;

    @JsonIgnore
    @OneToMany (mappedBy = "orderDetail2", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderHistory> orderHistories;
}
