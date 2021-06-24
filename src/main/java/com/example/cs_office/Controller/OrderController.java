package com.example.cs_office.Controller;

import com.example.cs_office.Model.Orders;
import com.example.cs_office.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //list order
    @GetMapping()
    public List<Orders> getOrder() {
        return orderService.getOrder();
    }

    //list order by status == fasle
    @GetMapping("/false")
    public List<Orders> getOrderByStatusFalse() {
        return orderService.getOrderByStatus(false);
    }

    //list order by status == true
    @GetMapping("/true")
    public List<Orders> getOrderByStatusTrue() {
        return orderService.getOrderByStatus(true);
    }

    // insert order
    @PostMapping
    public void insertOrder(@RequestBody Orders orders) {
        orderService.addNewOrder(orders);
    }

    //search order by id
    @GetMapping(path = "{orderId}")
    public Optional<Orders> getOrderById(
            @PathVariable("orderId") int orderId) {
        return orderService.getOrderById(orderId);
    }

    //delete order by id
    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);
    }

    //update order by id
    @PutMapping(path = "/{orderId}")
    public Orders updateOrder
    (@RequestBody Orders orders,
     @PathVariable("orderId") int id) {
        return orderService.updateOrder(orders, id);
    }

}
