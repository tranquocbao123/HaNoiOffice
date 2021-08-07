package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckRoom {
    private int idRoom;
    private String nameRoom;
    private String nameTypeRoom;
    private String nameBranch;
    private String time;
    private List<String> date;
}
