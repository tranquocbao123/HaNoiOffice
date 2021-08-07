package com.example.cs_office.Model.RoomBook;

import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleKLT {
    private List<Integer> listShift;
    private List<Integer> listService;
    private Date startDate;
}
