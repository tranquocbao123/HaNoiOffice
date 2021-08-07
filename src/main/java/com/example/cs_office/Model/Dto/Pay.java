package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pay {
    private int idOrderDetail;
    private double total;
}
