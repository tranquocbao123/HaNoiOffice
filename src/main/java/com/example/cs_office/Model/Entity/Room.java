package com.example.cs_office.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Code room may not be blank")
    private String codeRoom;
    @NotBlank(message = "Name may not be blank")
    private String name;
    @NotNull(message = "Price Service may not be null")
    private int soChoNgoi;
    @NotNull(message = "Description may not be null")
    private String description;
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
