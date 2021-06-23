package com.example.cs_office.Model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluate")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_User_Create")
    private int idUserCreate;
    private int level;
    private String content;
    private boolean status = true;
    @Column(name = "create_Date")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "id_order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order1;
}
