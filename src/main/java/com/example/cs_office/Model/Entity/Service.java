package com.example.cs_office.Model.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Price Service may not be null")
    private int priceService;
    private Date createDate = new Date();
    private boolean status = true;
    @NotBlank(message = "Description may not be blank")
    private String description;

    @JsonIgnore
    @OneToMany (mappedBy = "service1", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ServiceDetail> serviceDetails;

}
