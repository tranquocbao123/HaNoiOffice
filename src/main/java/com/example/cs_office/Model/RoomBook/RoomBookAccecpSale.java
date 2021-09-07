package com.example.cs_office.Model.RoomBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomBookAccecpSale {
    private int idCustomer;
    private int idRoom;
    List<ScheduleSale> schedules;
}
