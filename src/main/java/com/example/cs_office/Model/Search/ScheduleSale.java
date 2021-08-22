package com.example.cs_office.Model.Search;

import com.example.cs_office.Model.Entity.Shift;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleSale {
    private Date datePresent;
    private List<Shift> listShift;
}
