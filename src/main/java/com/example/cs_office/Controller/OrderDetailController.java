package com.example.cs_office.Controller;

import com.example.cs_office.Model.OrderDetail;
import com.example.cs_office.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderdetail")
@CrossOrigin("*")
public class OrderDetailController {

    @Autowired
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //list orderdetail
    @GetMapping()
    public List<OrderDetail> getOrderDetail() {
        return orderDetailService.getOrderDetail();
    }

    //list orderdetail by status == fasle
    @GetMapping("/false")
    public List<OrderDetail> getOrderDetailByStatusFalse() {
        return orderDetailService.getOrderDetailByStatus(false);
    }

    //list orderdetail by status == true
    @GetMapping("/true")
    public List<OrderDetail> getOrderDetailByStatusTrue() {
        return orderDetailService.getOrderDetailByStatus(true);
    }

    // insert orderdetail
    @PostMapping
    public void insertOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.addNewOrderDetail(orderDetail);
    }

    //search order detail by id
    @GetMapping(path = "{orderdetailId}")
    public Optional<OrderDetail> getOrderDetailById(
            @PathVariable("orderdetailId") int orderdetailId) {
        return orderDetailService.getOrderDetailById(orderdetailId);
    }

    //delete order detail by id
    @DeleteMapping(path = "{orderdetailId}")
    public void deleteOrder(
            @PathVariable("orderdetailId") int orderdetailId) {
        orderDetailService.deleteOrderDetail(orderdetailId);
    }

    //update order detail by id
    @PutMapping(path = "/{orderDetailId}")
    public OrderDetail updateOrderDetail
    (@RequestBody OrderDetail orderDetail,
     @PathVariable("orderDetailId") int id) {
        return orderDetailService.updateOrderDetail(orderDetail, id);
    }

}
