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
    @Column(name = "idUserCreate")
    private int idUserCreate;
    private int level;
    private String content;
    private boolean status = true;
    @Column(name = "createDate")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "idorder")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders1;
}
