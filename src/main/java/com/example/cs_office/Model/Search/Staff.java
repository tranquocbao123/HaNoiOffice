package com.example.cs_office.Model.Search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Staff {
    private int idRole;
    private int idBranch;
    private boolean gender;
    private String nameStaff;
}
