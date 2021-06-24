package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "priceservice")
public class PriceService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double value;
    @Column(name = "createDate")
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idService")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Service service;

    public PriceService(double value, Date createDate, boolean status, Service service) {
        this.value = value;
        this.createDate = createDate;
        this.status = status;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
