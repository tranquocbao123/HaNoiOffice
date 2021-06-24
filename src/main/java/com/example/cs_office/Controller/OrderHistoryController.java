package com.example.cs_office.Controller;

import com.example.cs_office.Model.OrderHistory;
import com.example.cs_office.Service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderhistory")
@CrossOrigin("*")
public class OrderHistoryController {

    @Autowired
    private final OrderHistoryService orderHistoryService;

    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }


    //list order history
    @GetMapping()
    public List<OrderHistory> getOrderHistory() {
        return orderHistoryService.getOrderHistory();
    }

    //list order history by status == fasle
    @GetMapping("/false")
    public List<OrderHistory> getOrderHistoryByStatusFalse() {
        return orderHistoryService.getOrderHistoryByStatus(false);
    }

    //list order history by status == true
    @GetMapping("/true")
    public List<OrderHistory> getOrderHistoryByStatusTrue() {
        return orderHistoryService.getOrderHistoryByStatus(true);
    }

    // insert order history
    @PostMapping
    public void insertOrderHistory(@RequestBody OrderHistory orderHistory) {
        orderHistoryService.addNewOrderHistory(orderHistory);
    }

    //search order history by id
    @GetMapping(path = "{orderhistoryId}")
    public Optional<OrderHistory> getOrderDetailById(
            @PathVariable("orderhistoryId") int orderhistoryId) {
        return orderHistoryService.getOrderHistoryById(orderhistoryId);
    }

    //delete order history by id
    @DeleteMapping(path = "{orderhistoryId}")
    public void deleteOrderHistory(
            @PathVariable("orderhistoryId") int orderhistoryId) {
        orderHistoryService.deleteOrderHistory(orderhistoryId);
    }

    //update order history by id
    @PutMapping(path = "/{orderHistoryId}")
    public OrderHistory updateOrderHistory
    (@RequestBody OrderHistory orderHistory,
     @PathVariable("orderHistoryId") int id) {
        return orderHistoryService.updateOrderHistory(orderHistory, id);
    }

}
