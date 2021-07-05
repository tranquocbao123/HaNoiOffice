package com.example.cs_office.Model.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Level may not be blank")
    private int level;
    @NotBlank(message = "Content may not be blank")
    private String content;
    @NotBlank(message = "Id user create may not be blank")
    private int idUserCreate;
    private boolean status = true;
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "idorder")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders1;
}
