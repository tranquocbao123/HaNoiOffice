package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.Entity.TypeRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomCustomer {
    private TypeRoom typeRoom;
    private Branch branch;
    private Room room;
    private List<ScheduleCustomer> listScheduleCustomer;
}
