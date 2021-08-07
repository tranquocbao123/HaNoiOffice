package com.example.cs_office.Model.InFoRoom;

import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.TypeRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InFoRoomByStartEndTypeRomBranch {
    private int idRoom;
    private String branch;
    private String typeRoom;
    private String time;
    private String Date;
    private String service;
}
