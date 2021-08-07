package com.example.cs_office.Model.RoomBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomBookLT {
    private int idCustomer;
    private String idRoom;
    private List<Integer> listIdServiceSelected;
    private List<Integer> listIdScheduleDetail;
    private Date startDate;
    private Date endDate;
    private double total;
}
