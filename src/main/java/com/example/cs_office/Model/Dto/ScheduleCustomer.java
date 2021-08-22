package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.Service;
import com.example.cs_office.Model.Entity.Shift;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleCustomer {
    private Date datePresent;
    private List<Service> listService;
    private List<Shift> listShift;
}
