package com.example.cs_office.Model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToTalDto {
    private int idOrderDetail;
    private double priceService;
    private double priceSchedule;
}
