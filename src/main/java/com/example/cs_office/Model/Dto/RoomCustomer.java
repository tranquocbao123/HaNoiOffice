package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomCustomer {
    private int idRoom;
    private int idOrderDetail;
    private String nameBranch;
    private String nameTypeRoom;
    private String nameRoom;
    private String time;
    private List<String> date;
    private String service;
    private double total;
    private boolean status;
}
