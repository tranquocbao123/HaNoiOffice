package com.example.cs_office.Model.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int id;
    private String codeRoom;
    private String name;
    private int soChoNgoi;
    private String description;
    private int typeRoom;
    private int branch;
}
