package com.example.cs_office.Model.Search;

import com.example.cs_office.Model.Dto.ScheduleCustomer;
import com.example.cs_office.Model.Entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchRoomSale {
    private Room room;
    private List<ScheduleSale> listScheduleSale;
}
