package com.example.cs_office.Controller;

import com.example.cs_office.Model.Entity.OrderHistory;
import com.example.cs_office.Service.OrderHistoryService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PathResources.ORDERHISTORY)
@CrossOrigin("*")
public class OrderHistoryController {

    @Autowired
    private final OrderHistoryService orderHistoryService;

    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }


    //list order history
    @GetMapping(PathResources.FIND_ALL)
    public List<OrderHistory> getOrderHistory() {
        return orderHistoryService.getOrderHistory();
    }

    //list order history by status == fasle
    @GetMapping(PathResources.FIND_BY_STATUSFALSE)
    public List<OrderHistory> getOrderHistoryByStatusFalse() {
        return orderHistoryService.getOrderHistoryByStatus(false);
    }

    //list order history by status == true
    @GetMapping(PathResources.FIND_BY_STATUSTRUE)
    public List<OrderHistory> getOrderHistoryByStatusTrue() {
        return orderHistoryService.getOrderHistoryByStatus(true);
    }

    // insert order history
    @PostMapping(PathResources.SAVE)
    public void insertOrderHistory(@RequestBody OrderHistory orderHistory) {
        orderHistoryService.addNewOrderHistory(orderHistory);
    }

    //search order history by id
    @GetMapping(path = PathResources.FIND_BY_ID)
    public Optional<OrderHistory> getOrderDetailById(
            @PathVariable("id") int orderhistoryId) {
        return orderHistoryService.getOrderHistoryById(orderhistoryId);
    }

    //delete order history by id
    @DeleteMapping(path = PathResources.DELETEBYID)
    public void deleteOrderHistory(
            @PathVariable("id") int orderhistoryId) {
        orderHistoryService.deleteOrderHistory(orderhistoryId);
    }

    //update order history by status
    @PutMapping(PathResources.UPDATESTATUSFALSE)
    public void updateOrderHistoryStatus(
            @RequestBody OrderHistory orderHistory
    ) {
        orderHistoryService.updateOrderHistoryStatus(orderHistory);
    }

    //update order history black by status
    @PutMapping(path = PathResources.UPDATESTATUSTRUE)
    public void updateOrderHistoryBlack(
            @RequestBody OrderHistory orderHistory
    ) {
        orderHistoryService.updateOrderHistoryBlack(orderHistory);
    }

    //update order history by id
    @PutMapping(path = PathResources.UPDATEBYID)
    public OrderHistory updateOrderHistory
    (@RequestBody OrderHistory orderHistory,
     @PathVariable("id") int orderHistoryId) {
        return orderHistoryService.updateOrderHistory(orderHistory, orderHistoryId);
    }

}
