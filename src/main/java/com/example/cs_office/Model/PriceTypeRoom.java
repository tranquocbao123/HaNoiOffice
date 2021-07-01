package com.example.cs_office.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceTypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double value;
    private Date createDate = new Date();
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "idTypeRoom")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TypeRoom typeRoom1;

    public PriceTypeRoom(double value, Date createDate, boolean status, TypeRoom typeRoom1) {
        this.value = value;
        this.createDate = createDate;
        this.status = status;
        this.typeRoom1 = typeRoom1;
    }
}
