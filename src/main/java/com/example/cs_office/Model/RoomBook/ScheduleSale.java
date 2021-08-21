package com.example.cs_office.Model.RoomBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleSale {
    private List<Integer> listShift;
    private List<Integer> listService;
    private Date startDate;
    private Date endDate;
}
