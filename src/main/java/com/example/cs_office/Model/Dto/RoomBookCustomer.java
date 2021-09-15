package com.example.cs_office.Model.Dto;

import com.example.cs_office.Model.Entity.OrderDetail;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.Entity.Schedule;
import com.example.cs_office.Model.Entity.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomBookCustomer {
    private int idOrderDetail;
    private Date createDate;
    private String nameRoom;
    private double price;
    private boolean statusOrder;
    private boolean statusPay;
}
