package com.example.cs_office.Model.RoomBook;

import com.example.cs_office.Model.Entity.Scheduledetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomBookLeTan {
    private int idScheduleDetail;
    private int idCustomer;
    private int idBranch;
    private String nameCustomer;
    private String nameRoom;
    private String shift;
    private List<String> listService;
    private boolean status;
}
