package com.example.cs_office.Model.Search;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class SearchRoom {
    private String idTypeRoom;
    private String idBranch;
    private Date startDate;
    private Date endDate;
}
