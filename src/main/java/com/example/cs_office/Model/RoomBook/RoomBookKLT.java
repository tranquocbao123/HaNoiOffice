package com.example.cs_office.Model.RoomBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomBookKLT {
    private int idCustomer;
    private String idRoom;
    List<ScheduleKLT> schedules;
    private double total;
}
