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
    private int level;
    private String content;
    private int idUserCreate;
    private boolean status = true;
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "idorder")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders1;
}
