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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status = true;
    @Column(name = "createDate")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "idcustomer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @JsonIgnore
    @OneToMany (mappedBy = "orders2", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany (mappedBy = "orders1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Evaluate> evaluates;

}
