package com.example.cs_office.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Start date may not be blank")
    private java.sql.Date startDate;
    @NotNull(message = "End date may not be blank")
    private java.sql.Date endDate;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idOrderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail;

    @JsonIgnore
    @OneToMany (mappedBy = "schedule", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ServiceDetail> serviceDetails;

    @JsonIgnore
    @OneToMany (mappedBy = "schedule", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Scheduledetail> scheduledetails;
}

