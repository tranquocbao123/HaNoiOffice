package com.example.cs_office.Model.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicedetail")
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idService")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Service service1;

    @ManyToOne
    @JoinColumn(name = "idorderdetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail;

}
