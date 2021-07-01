package com.example.cs_office.Controller;

import com.example.cs_office.Model.OrderDetail;
import com.example.cs_office.Service.OrderDetailService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.ORDERDETAIL)
@CrossOrigin("*")
public class OrderDetailController {

    @Autowired
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //list orderdetail
    @GetMapping(PathResources.FIND_ALL)
    public List<OrderDetail> getOrderDetail() {
        return orderDetailService.getOrderDetail();
    }

    //list orderdetail by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<OrderDetail> getOrderDetailByStatusFalse() {
        return orderDetailService.getOrderDetailByStatus(false);
    }

    //list orderdetail by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<OrderDetail> getOrderDetailByStatusTrue() {
        return orderDetailService.getOrderDetailByStatus(true);
    }

    // insert orderdetail
    @PostMapping(PathResources.SAVE)
    public void insertOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.addNewOrderDetail(orderDetail);
    }

    //search order detail by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<OrderDetail> getOrderDetailById(
            @PathVariable("id") int orderdetailId) {
        return orderDetailService.getOrderDetailById(orderdetailId);
    }

    //delete order detail by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteOrder(
            @PathVariable("id") int orderdetailId) {
        orderDetailService.deleteOrderDetail(orderdetailId);
    }

    //update order detail by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateOrderDetailStatus(
            @RequestBody OrderDetail orderDetail
    ) {
        orderDetailService.updateOrderDetailStatus(orderDetail);
    }

    //update order detail black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateOrderDetailBlack(
            @RequestBody OrderDetail orderDetail
    ) {
        orderDetailService.updateOrderDetailBlack(orderDetail);
    }

    //update order detail by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public OrderDetail updateOrderDetail
    (@RequestBody OrderDetail orderDetail,
     @PathVariable("id") int orderDetailId) {
        return orderDetailService.updateOrderDetail(orderDetail, orderDetailId);
    }

}
