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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status = true;
    @Column(name = "create_Date")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "id_customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @JsonIgnore
    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany (mappedBy = "order1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Evaluate> evaluates;

    public Order(int id, boolean status, Date createDate, Customer customer) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.customer = customer;
    }

    public Order(boolean status, Date createDate, Customer customer) {
        this.status = status;
        this.createDate = createDate;
        this.customer = customer;
    }

}
