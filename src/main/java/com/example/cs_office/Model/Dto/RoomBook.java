package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomBook {
    private int idCustomer;
    private String nameCustomer;
    private String numberPhone;
    private int idOrderDetail;
    private Date createDate;
    private boolean statusOrder;
    private boolean statusPay;
}
