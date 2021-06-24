package com.example.cs_office.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int idPriceService;
    private Date createDate = new Date();
    private boolean status = true;

    @JsonIgnore
    @OneToMany (mappedBy = "service", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PriceService> priceServices;

    @JsonIgnore
    @OneToMany (mappedBy = "service1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ServiceDetail> serviceDetails;

    public Service( String name,int idPriceService, Date createDate, boolean status) {
        this.name = name;
        this.idPriceService = idPriceService;
        this.createDate = createDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdPriceService() {
        return idPriceService;
    }

    public void setIdPriceService(int idPriceService) {
        this.idPriceService = idPriceService;
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

    public Collection<PriceService> getPriceServices() {
        return priceServices;
    }

    public void setPriceServices(Collection<PriceService> priceServices) {
        this.priceServices = priceServices;
    }
}
