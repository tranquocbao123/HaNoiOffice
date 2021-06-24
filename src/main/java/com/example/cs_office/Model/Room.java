package com.example.cs_office.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "createDate")
    private Date createDate = new Date();
    private boolean status = true;

    @JsonIgnore
    @OneToMany (mappedBy = "room", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "idTypeRoom")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TypeRoom typeRoom;

    @ManyToOne
    @JoinColumn(name = "idBranch")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Branch branch1;
}
