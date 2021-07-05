package com.example.cs_office.Model.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Code service may not be blank")
    private String codeService;
    @NotBlank(message = "Name may not be blank")
    private String name;
    private Date createDate = new Date();
    private boolean status = true;
    private String description;

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

}