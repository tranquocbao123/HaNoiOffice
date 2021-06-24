package com.example.cs_office.Controller;

import com.example.cs_office.Model.Order;
import com.example.cs_office.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //list order
    @GetMapping()
    public List<Order> getOrder() {
        return orderService.getOrder();
    }

    //list order by status == fasle
    @GetMapping("/false")
    public List<Order> getOrderByStatusFalse() {
        return orderService.getOrderByStatus(false);
    }

    //list order by status == true
    @GetMapping("/true")
    public List<Order> getOrderByStatusTrue() {
        return orderService.getOrderByStatus(true);
    }

    // insert order
    @PostMapping
    public void insertOrder(@RequestBody Order order) {
        orderService.addNewOrder(order);
    }

    //search order by id
    @GetMapping(path = "{orderId}")
    public Optional<Order> getOrderById(
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
    public Order updateOrder
    (@RequestBody Order order,
     @PathVariable("orderId") int id) {
        return orderService.updateOrder(order, id);
    }
}
